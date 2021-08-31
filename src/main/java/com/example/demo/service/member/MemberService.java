package com.example.demo.service.member;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.member.dto.MemberDTO;
import com.example.demo.domain.member.dto.MemberListDTO;
import com.example.demo.domain.PageDTO;
import com.example.demo.domain.member.dto.UpdateInfoDTO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.Password;

import java.util.ArrayList;
import java.util.List;


public interface MemberService {

    public int join(Member member , Password password);

    public Member get(String memberId);

    public String idCheck(String memberId);

    public SessionMember login(String loginId, String loginPw);

    public void updateInfo(UpdateInfoDTO updateInfoDTO);

    public int update(MemberDTO memberDTO);

    public void memberOut(String memberId);

    public int totalCount();

    public  List<MemberListDTO> getList(Criteria cri);

    public void deleteMember(Long memberNo);

    public int delete(Long memberNo);

    public  String adminChk(String memberId);

}
