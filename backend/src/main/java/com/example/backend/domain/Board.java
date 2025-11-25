package com.example.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Board {
    @Id @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    @ColumnDefault("0")
    private Long registerId;

    @ColumnDefault("0")
    private int hitCount;


}
