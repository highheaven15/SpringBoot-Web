package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.BaseTimeEntity;
import com.example.demo.domain.member.dto.vo.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name="memberSeq",sequenceName ="memberSeq",initialValue = 1,allocationSize = 1)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="memberSeq")
    private Long memberNo;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberGender;

    @Column(nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String memberAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String memberId, String memberName,
                  String memberGender, String memberEmail, String memberAge, Role role) {
        this.memberId = memberId;

        this.memberName = memberName;
        this.memberGender = memberGender;
        this.memberEmail = memberEmail;
        this.memberAge = memberAge;
        this.role = role;
    }

    public Member update( String memberName, String memberEmail, String memberGender, String memberAge) {

        this.memberName =memberName;
        this.memberEmail =memberEmail;
        this.memberGender =memberGender;
        this.memberAge =memberAge;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}