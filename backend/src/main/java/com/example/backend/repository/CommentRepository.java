package com.example.backend.repository;

import com.example.backend.domain.Comment;
import com.example.backend.dto.comment.CommentListItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard_Id(Long boardId);

    @Query("""
            select new com.example.backend.dto.comment.CommentListItemResponse (
                c.member.memberName,
                c.contents,
                c.createDate
            )
            from Comment c
            where c.board.id = :boardId
            order by c.createDate DESC
            """)
    List<CommentListItemResponse> findCommentListByBoardId(@Param("boardId")Long boardId);
}
