package com.example.backend.repository;

import com.example.backend.domain.Board;
import com.example.backend.dto.board.BoardListItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    // 게시글 목록 리스트 조회
    @Query("""
            select new com.example.backend.dto.board.BoardListItemResponse  (
                b.id,
                b.title,
                b.hitCount,
                b.member.memberName,
                b.createDate,
                b.lastModifiedDate
            )
            from Board b
            order by b.id desc
            """)
    List<BoardListItemResponse> findBoardListDTO();
}
