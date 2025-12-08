package com.example.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String memberName;

    private String memberId;

    private String memberPw;

    // 관리자: ADMIN
    // 일반 회원: USER
    // 게스트 : GUEST
    @ColumnDefault("USER")
    private String roleType;
}
