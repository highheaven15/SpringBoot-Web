package com.example.demo.domain.account;

import lombok.Data; //getter, setter 를 생성해준다.

@Data
public class AccountDTO { //DTO : Data Transfer Object
    private Long account_no; //계좌 시퀸스
    private String member_id; //멤버 아이디


    private String account_number; //계좌 번호
    private String account_pw; //계좌 비밀번호

    private String birth; //주민등록번호 앞자리
    private String phone_number; //전화번호

    private Long balance; //잔액
    private Long money;//현금 이동





}