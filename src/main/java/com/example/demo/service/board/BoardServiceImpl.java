package com.example.demo.service.board;


import com.example.demo.domain.board.AttachFileDTO;
import com.example.demo.domain.board.BoardFileVO;
import com.example.demo.domain.board.BoardVO;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberRepository;
import com.example.demo.mapper.board.BoardFileMapper;
import com.example.demo.mapper.board.BoardMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardFileMapper fileMapper;

    @Autowired
    private MemberRepository repository;



    @Transactional
    @Override
    public void register(BoardVO board) {

        Member member = repository.findByMemberId(board.getWriter());
        Long memberNO = member.getMemberNo();
        board.setMno(memberNO);

        mapper.insertSelectKey(board);

        System.out.println("fileName" + board.getFileName());

//file 없다
        if(board.getFileName() == null){
            return;

        } else if(board.getFileName() != "") {

            AttachFileDTO attachFileDTO = new AttachFileDTO();
            attachFileDTO.setFileName(board.getFileName());
            attachFileDTO.setBno(board.getBno());

            fileMapper.insert(attachFileDTO);
        }

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO get(Long bno) {
        mapper.updateHit(bno);

        BoardVO board = mapper.read(bno);
        String fileName = fileMapper.getFileNameByBno(bno);
        board.setFileName(fileName);

        return board;
    }

    @Transactional
    @Override
    public boolean modify(BoardVO board) {
//다 지워주고
        fileMapper.deleteAll(board.getBno());

        boolean modifyResult = mapper.update(board) == 1;

        if(modifyResult && board.getFileName() != ""){

            AttachFileDTO attachFileDTO = new AttachFileDTO();
           // System.out.println("imple에서 set"+ board.getFileName());
            attachFileDTO.setFileName(board.getFileName());
            attachFileDTO.setBno(board.getBno());

            fileMapper.insert(attachFileDTO);

        }
        return modifyResult;
    }
    @Transactional
    @Override
    public boolean remove(Long bno) {
        fileMapper.deleteAll(bno);
        return mapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {

        return mapper.getListWithPaging(cri);
    }

    @Override
    public List<BoardFileVO> getFileList(Long bno) {
        return fileMapper.findByBno(bno);
    }

    @Override
    public int getTotal(Criteria cri) {
        return mapper.getTotalCount(cri);
    }



  /*  @Override
    public List<BoardFileVO> getFileList(Long bno) {
        return null;
    }*/




}

