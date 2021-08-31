package com.example.demo.mapper.gallery;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.gallery.GalleryFileVO;
import com.example.demo.domain.gallery.GalleryS3DTO;
import com.example.demo.domain.gallery.GalleryVO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GalleryMapper {

    public List<GalleryVO> getList();

    public void insert(GalleryS3DTO gallery);

    public Integer insertSelectKey(GalleryS3DTO gallery);

    public GalleryS3DTO read(Long bno);

    public int delete(Long bno);

    public int update(GalleryS3DTO gallery);

    public int getTotalCount(Criteria cri);

    public List<GalleryVO> getListWithPaging(Criteria cri);

    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

    public List<GalleryFileVO> findByBno(Long bno);

    public void updateHit(@Param("bno") Long bno);

    public List<GalleryS3DTO> getFiles();
}
