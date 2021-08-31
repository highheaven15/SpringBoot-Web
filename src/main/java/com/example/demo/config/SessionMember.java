package com.example.demo.config;

// SessionMember 에는 인증된 사용자 정보만 필요하다.
// 그 외의 정보는 필요 없으니 name, email, gender, age 필드로 선언한다.
//SessionMember 대신 Member 클래스를 사용하면 직렬화 관련 에러가 발생한다.
//그렇다고 해서 Member 클래스에 Serializable를 implements해주면 안된다
//Member 클래스는 엔티티 클래스이므로 다른 엔티티와 관계가 형성될 수 있다.
//Member 클래스가 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지 포함된다. 이는 성능 이슈, 부수 효과가 발생한다.
//이러한 이유로 인해 직렬화 기능을 갖은 세션 Dto를 하나 추가로 만드는게 유지보수에 도움이 된다.


import com.example.demo.domain.member.dto.vo.Role;
import com.example.demo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String age;
    private Role role;

    @Builder
    public SessionMember(Member member) {
        this.id = member.getMemberId();
        this.name = member.getMemberName();
        this.email = member.getMemberEmail();
        this.gender = member.getMemberGender();
        this.age = member.getMemberAge();
        this.role = member.getRole();
        }
    }