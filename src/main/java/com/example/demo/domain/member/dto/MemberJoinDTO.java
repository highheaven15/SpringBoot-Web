package com.example.demo.domain.member.dto;


import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.dto.vo.Role;
import lombok.Data;


@Data
public class MemberJoinDTO {

    private String memberId;

    private String memberName;

    private String memberGender;

    private String memberEmail;
   
    private String memberAge;

    private Role role;

    public Member toEntity(){
        Member member = Member.builder()
                .memberId(memberId)
                .memberName(memberName)
                .memberGender(memberGender)
                .memberEmail(memberEmail)
                .memberAge(memberAge)
                .role(role)
                .build();

        return member;
    }

}
