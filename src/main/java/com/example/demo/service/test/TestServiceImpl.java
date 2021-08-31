package com.example.demo.service.test;


import com.example.demo.domain.member.dto.vo.TestVo;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberRepository;
import com.example.demo.mapper.member.MemberMapper;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class TestServiceImpl implements TestService{

    private MemberMapper mapper;

    private MemberRepository memberRepository;

    //총 페이지는 10개씩 보여주도록
    private static final int ALL_PAGE_COUNT = 10;
    //한 페이지당 총 글 개수는 10개씩
    private static final int PAGE_POST_COUNT = 10;

    @Override
    public void insert() {

    }

    @Override
    public TestVo get() {
        return null;
    }

    @Override
    public List<Member> getMemberList(int pageNum) {
//        Page<Member> page = memberRepository.findAll(PageRequest.of())
        return null;
    }
}
