package com.example.demo.service.test;

import com.example.demo.domain.member.dto.vo.TestVo;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.dto.vo.TestVo;
import com.example.demo.domain.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    public void insert();

    public TestVo get();

    public List<Member> getMemberList(int pageNum);
}
