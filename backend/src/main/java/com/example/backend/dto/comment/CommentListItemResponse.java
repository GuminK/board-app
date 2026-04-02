package com.example.backend.dto.comment;

import java.time.LocalDateTime;

public record CommentListItemResponse(
        Long commentId,
        String memberId,
        String memberName,
        String contents,
        LocalDateTime createDate
) {
}
