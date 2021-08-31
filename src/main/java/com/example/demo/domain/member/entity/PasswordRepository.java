package com.example.demo.domain.member.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordRepository extends JpaRepository<Password,Long> {
    @Query("select p.pw from Password p where p.member.memberId=?1")
    public String findPwByMemberId(String memberId);

    @Query("select p from Password p where p.member.memberId=?1")
    public Password findByMemberId(String memberId);
}
