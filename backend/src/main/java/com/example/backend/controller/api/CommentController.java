package com.example.backend.controller.api;

import com.example.backend.dto.comment.CommentCreateRequest;
import com.example.backend.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<?> getCommentsList(@PathVariable Long boardId){
        return null;
    }

    @PostMapping("/board/{boardId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long boardId, Authentication authentication, @RequestBody CommentCreateRequest request){
        commentService.saveComment(boardId, authentication.getName(), request);
        return ResponseEntity.status(201).body(Map.of("message", "create comment success"));
    }

}
