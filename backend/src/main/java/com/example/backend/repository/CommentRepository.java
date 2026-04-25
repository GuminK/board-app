package com.example.backend.repository;

import com.example.backend.domain.Comment;
import com.example.backend.dto.comment.CommentListItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 댓글 목록 리스트 조회
    @Query("""
            select new com.example.backend.dto.comment.CommentListItemResponse (
                c.id,
                c.member.memberId,
                c.member.memberName,
                c.contents,
                c.createDate
            )
            from Comment c
            where c.board.id = :boardId
            order by c.createDate ASC
            """)
    List<CommentListItemResponse> findCommentItemListByBoardId(@Param("boardId")Long boardId);

    List<Comment> findCommentListByBoardId(@Param("boardId")Long boardId);

    void deleteByBoard_Id(Long boardId);


}
