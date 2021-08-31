package com.example.demo.domain.board;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardVO {

    private Long bno;
    private Long mno;

    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updateDate;

    private int replyCnt;
    private int hits;    //조회수

    private String fileName;
    private List<BoardFileVO> FileList;

}
