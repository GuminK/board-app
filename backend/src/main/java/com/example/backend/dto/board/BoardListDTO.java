package com.example.backend.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardListDTO {
    // 게시물 목록 불러올 때 필요한 데이터
    Long id;
    String title;
    int hitCount;
    String memberName;
    LocalDateTime createDate;
    LocalDateTime lastModifiedDate;
}
