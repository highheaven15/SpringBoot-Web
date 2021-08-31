package com.example.demo.service.gallery;


import com.example.demo.domain.Criteria;

import com.example.demo.domain.gallery.AttachFileDTO;
import com.example.demo.domain.gallery.GalleryFileVO;
import com.example.demo.domain.gallery.GalleryS3DTO;
import com.example.demo.domain.gallery.GalleryVO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberRepository;
import com.example.demo.mapper.gallery.GalleryFileMapper;
import com.example.demo.mapper.gallery.GalleryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper mapper;

    @Autowired
    private GalleryFileMapper fileMapper;

    @Autowired
    private MemberRepository repository;


    @Transactional
    @Override
    public void register(GalleryS3DTO gallery) {

        Member member = repository.findByMemberId(gallery.getWriter());
        Long memberNO = member.getMemberNo();
        gallery.setMno(memberNO);

        mapper.insertSelectKey(gallery);

        if (gallery.getFileName() != "") {

            fileMapper.insert(gallery);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public GalleryS3DTO get(Long bno) {

        GalleryS3DTO gallery = mapper.read(bno);

        // System.out.println("read: "+ gallery);

        return gallery;
    }

    @Transactional
    @Override
    public boolean modify(GalleryS3DTO gallery) {
//다 지워주고
        fileMapper.deleteAll(gallery.getBno());
//update가 성공이면
        boolean modifyResult = mapper.update(gallery) == 1;

        if (modifyResult && gallery.getFileName() != "" && gallery.getUrl() != null) {
            fileMapper.insert(gallery);
        }
        return modifyResult;
    }

    @Transactional
    @Override
    public boolean remove(Long bno) {
        fileMapper.deleteAll(bno);
        return mapper.delete(bno) == 1;
    }


    @Transactional
    @Override
    public List<GalleryS3DTO> getFiles() {

        List<GalleryS3DTO> gallery = mapper.getFiles();
        // System.out.println("list:"+ gallery);
        return gallery;
    }


}
