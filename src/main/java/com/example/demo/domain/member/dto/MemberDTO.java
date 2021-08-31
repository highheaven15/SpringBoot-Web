package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.dto.vo.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {

    private Long member_no;

    private String member_id;

    private String member_name;

    private String member_gender;

    private String member_email;

    private String member_age;

    private Role role;

}
