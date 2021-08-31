package com.example.demo.domain.member;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //Entity 에 변화가 생겼을때의 리스너
public abstract class BaseTimeEntity {

    @CreatedDate //Entity 생성이 될 때의 시간 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate //마지막에 Entity가 수정된 시간 자동 저장
    private LocalDateTime modifiedDate;
}