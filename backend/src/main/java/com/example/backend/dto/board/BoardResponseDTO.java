package com.example.backend.dto.board;

import com.example.backend.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String contents;
    private Long registerId;
    private int hitCount;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public BoardResponseDTO(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.registerId = board.getRegisterId();
        this.hitCount = board.getHitCount();
        this.createDate = board.getCreateDate();
        this.lastModifiedDate = board.getLastModifiedDate();
    }


}
