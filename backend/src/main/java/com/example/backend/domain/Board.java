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

//    TODO: Member와 연결시켜 글 작성자로 취급할 것.
    @ColumnDefault("0")
    private Long registerId;

    @ColumnDefault("0")
    private int hitCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
