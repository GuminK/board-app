package com.example.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Board {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String contents;

    // 기존에 registerId를 쓴 이유는 Member를 안만든 상태에서 테스트했기 때문에 이제 Member로 바로 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", nullable = false)
    private Member member;

    @ColumnDefault("0")
    private int hitCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
