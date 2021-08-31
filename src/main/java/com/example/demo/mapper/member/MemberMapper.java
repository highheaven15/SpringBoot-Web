package com.example.demo.mapper.member;

import com.example.demo.domain.Criteria;
import com.example.demo.domain.member.dto.MemberDTO;
import com.example.demo.domain.member.dto.MemberJoinDTO;
import com.example.demo.domain.member.dto.MemberListDTO;
import com.example.demo.domain.member.dto.UpdateInfoDTO;
import com.example.demo.domain.member.dto.vo.Role;
import com.example.demo.domain.member.entity.Member;

import java.util.List;

public interface MemberMapper {

    public void insert(MemberJoinDTO memberJoinDTO);

    public Member get(String memberId);

    public Member findMemberForIdCheck(String memberId);

    public void update(UpdateInfoDTO updateInfoDTO);

    public int modify(MemberDTO memberDTO);

    public void updatePw(UpdateInfoDTO updateInfoDTO);

    public List<MemberListDTO> getListWithPaging(Criteria cri);

    //jpql 이 안되서 일단 일시적으로 하는 것
    public void memberOut(Long memberNo);

    public int remove(Long memberNo);

    public Role adminChk(String MemberId);
}
