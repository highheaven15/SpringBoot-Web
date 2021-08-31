package com.example.demo.domain.gallery;

import lombok.Data;

import java.util.Date;

@Data
public class GalleryS3DTO {

    private Long fno;
    private Long bno;
    private Long mno;
    private String fileName;
    private String url;

    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updateDate;

}
