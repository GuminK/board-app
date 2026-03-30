package com.example.backend.controller.api;

import com.example.backend.dto.comment.CommentCreateRequest;
import com.example.backend.dto.comment.CommentListItemResponse;
import com.example.backend.dto.comment.CommentUpdateRequest;
import com.example.backend.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    // 댓글 목록 조회
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentListItemResponse>> getCommentsList(@PathVariable Long boardId){
        return ResponseEntity.ok(commentService.getCommentList(boardId));
    }

    // 댓글 생성
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long boardId, Authentication authentication, @RequestBody CommentCreateRequest request){
        commentService.saveComment(boardId, authentication.getName(), request);
        return ResponseEntity.status(201).body(Map.of("message", "create comment success"));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, Authentication authentication){
        commentService.deleteComment(commentId, authentication.getName());
        return ResponseEntity.status(204).body(Map.of("message", "delete comment success"));
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, Authentication authentication, @RequestBody CommentUpdateRequest request){
        commentService.updateComment(commentId, authentication.getName(), request);
        return ResponseEntity.ok().body(Map.of("message", "update comment success"));
    }

}
