package com.example.demo.domain.member.dao;

import lombok.Data;

@Data
public class LoginMember {
    private String id;

    private String name;
    private String email;
    private String gender;
    private String age;
}
