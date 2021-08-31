package com.example.demo.domain.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberListDTO {
    private Long member_no;
    private String member_id;
    private String member_email;
    private String member_name;
    private String member_age;
    private String member_gender;

}
