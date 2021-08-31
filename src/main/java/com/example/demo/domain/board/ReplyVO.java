package com.example.demo.domain.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyVO {

    private Long rno;
    private Long bno;
    private Long mno;

    private String reply;
    private String replyer;
    private Date replyDate;
    private Date updateDate;

}
