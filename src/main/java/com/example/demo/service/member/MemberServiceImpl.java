package com.example.demo.service.member;

import com.example.demo.config.SessionMember;
import com.example.demo.domain.member.dto.MemberDTO;
import com.example.demo.domain.member.dto.MemberListDTO;
import com.example.demo.domain.member.dto.UpdateInfoDTO;
import com.example.demo.domain.Criteria;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberRepository;
import com.example.demo.domain.member.entity.Password;
import com.example.demo.domain.member.entity.PasswordRepository;
import com.example.demo.mapper.member.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordRepository passwordRepository;

    @Override
    public int join(Member member, Password password) {

        if(memberRepository.findByMemberId(member.getMemberId()) != null ){
            return 1; //회원가입 실패 -- 이미 존재하는 아이디
        }
        memberRepository.save(member);
        passwordRepository.save(password);
        return 0; //회원가입 정상 종료
    }

    @Override
    public Member get(String memberId) {

        Member member = memberRepository.findByMemberId(memberId);
     /*   System.out.println("memberServiceImpl MEMBER:" + member.getMemberId());
        System.out.println("memberServiceImpl MEMBER NAME:" + member.getMemberName());*/
        return member;
    }

    @Override
    public String idCheck(String memberId) {
//       Member member = memberMapper.findMemberForIdCheck(memberId);
        String member = memberRepository.findMemberIdForIdCheck(memberId);
        if(member != null){
            return "1";
        }
        return "0";
    }

    @Override
    public SessionMember login(String loginId, String loginPw) {
        String pw = passwordRepository.findPwByMemberId(loginId);
        if(pw!=null && pw.equals(loginPw)){
            Member member = memberRepository.findByMemberId(loginId);
            SessionMember sessionMember = new SessionMember(member);
           // System.out.println(sessionMember.getRole());
            return sessionMember;
        }
        return null;
    }

    @Override
    public void updateInfo(UpdateInfoDTO updateInfoDTO) {
        Member member = memberRepository.findByMemberId(updateInfoDTO.getMemberId());
        updateInfoDTO.setMemberNo(member.getMemberNo());
        memberMapper.update(updateInfoDTO);
        memberMapper.updatePw(updateInfoDTO);
    }

    @Override
    public int update(MemberDTO memberDTO) {

        return memberMapper.modify(memberDTO);
    }

    @Override
    public void memberOut(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Password password = passwordRepository.findByMemberId(memberId);
        passwordRepository.delete(password);
        memberRepository.delete(member);
//        memberRepository.deleteById(member.getMemberNo());
    }

    @Override
    public int totalCount() {
        int total = memberRepository.findAll().size();
        return total;
    }

    @Override
    public List<MemberListDTO> getList(Criteria cri) {
        List<MemberListDTO> list = memberMapper.getListWithPaging(cri);
        return list;
    }

    @Override
    public void deleteMember(Long memberNo) {
        memberRepository.deleteById(memberNo);
    }

    @Override
    public int delete(Long memberNo) {
        return memberMapper.remove(memberNo);
    }

    @Override
    public String adminChk(String memberId) {
        String role =  memberMapper.adminChk(memberId).toString();
       // System.out.println(role);
        if (role == "ADMIN") {
            return "1";
        }
        return "0";
    }
}
