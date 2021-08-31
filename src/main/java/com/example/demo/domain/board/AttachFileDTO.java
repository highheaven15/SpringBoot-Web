package com.example.demo.domain.board;


import lombok.Data;

@Data
public class AttachFileDTO {        //첨부파일에 대한 데이터를 저장

    private Long bno;
    private String fileName;

    // 이미지인지 체크
}
