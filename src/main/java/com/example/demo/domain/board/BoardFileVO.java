package com.example.demo.domain.board;

import lombok.Data;

//테이블 데이터
@Data
public class BoardFileVO {

    private Long fno;
    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;

    private Long bno;

}
