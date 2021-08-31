package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageDTO {

    private int startPage;      // 시작페이지    1 | 11 | 21
    private int endPage;        // 끝페이지     10 | 20 | 30
    private boolean prev, next;      // 이전, 다음 페이지 유무

    private int total;  // 전체 레코드수
    private Criteria cri;

    public PageDTO(Criteria cri, int total){  //생성자

        this.cri = cri;
        this.total = total;

//Math.ceil - 소수점을 올림으로 처리
        this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
//페이지 시작 번호
        this.startPage = this.endPage - 9;

//게시물 갯수에 맞는 페이지 끝 번호
        int realEnd = (int)(Math.ceil((total * 1.0)/cri.getAmount()));

        if(realEnd < this.endPage){      // 실제 끝 페이지가 끝 페이지보다 작으면
            this.endPage = realEnd;     // 끝 페이지를 실제 끝 페이지 값으로 변경
        }

//이전 페이지의 경우는 시작번호가 1보다 크면 존재함
        this.prev = this.startPage > 1;

//다음 페이지의 경우 realEnd가 끝 번호보다 큰 경우에만 존재재
        this.next = this.endPage < realEnd;
    }
}
