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
public class Board {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String contents;

    @ColumnDefault("0")
    private Long registerId;

    @ColumnDefault("0")
    private int hitCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Builder
    public Board(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    @Builder
    public Board(Long id, String title, String contents){
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

}
