package com.example.backend.dto.comment;

public record CommentCreateRequest(
        String contents,
        Long parentCommentId
) {
}
