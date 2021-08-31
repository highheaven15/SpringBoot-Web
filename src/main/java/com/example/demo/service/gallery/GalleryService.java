package com.example.demo.service.gallery;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.gallery.GalleryFileVO;
import com.example.demo.domain.gallery.GalleryS3DTO;
import com.example.demo.domain.gallery.GalleryVO;

import java.util.List;

public interface GalleryService {

    public void register(GalleryS3DTO gallery);

    public GalleryS3DTO get(Long bno);

    public boolean modify(GalleryS3DTO gallery);

    public boolean remove(Long bno);

    public List<GalleryS3DTO> getFiles();

}
