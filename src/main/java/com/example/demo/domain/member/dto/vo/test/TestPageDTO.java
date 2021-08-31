package com.example.demo.domain.member.dto.vo.test;

import com.example.demo.domain.member.entity.Member;
import lombok.Data;

@Data
public class TestPageDTO {
    private Long memberNo;
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberAge;
    private String memberGender;

    public TestPageDTO(Member member) {
        this.memberNo = member.getMemberNo();
        this.memberId = member.getMemberId();
        this.memberName = member.getMemberName();
        this.memberEmail = member.getMemberEmail();
        this.memberAge = member.getMemberAge();
        this.memberGender = member.getMemberGender();
    }
}
