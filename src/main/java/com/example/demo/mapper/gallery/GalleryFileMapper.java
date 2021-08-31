package com.example.demo.mapper.gallery;

import com.example.demo.domain.gallery.AttachFileDTO;
import com.example.demo.domain.gallery.GalleryFileVO;
import com.example.demo.domain.gallery.GalleryS3DTO;

import java.util.List;

public interface GalleryFileMapper {

    public void insert(GalleryS3DTO file);

    public void delete(Long fno);

    public List<GalleryFileVO> findByBno(Long bno);

    public void deleteAll(Long bno);

    public List<GalleryFileVO> getOldFiles();

    public GalleryS3DTO getInfoByBno(Long bno);

    public List<GalleryS3DTO> getFiles();
}

