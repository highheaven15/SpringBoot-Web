package com.example.demo.domain.gallery;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GalleryVO {

    private Long bno;
    private Long mno;

    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updateDate;

    private String fileName;
    private List<GalleryFileVO> FileList;
    private String fileUrl;

}
