package com.example.backend.dto.comment;

import java.time.LocalDateTime;

public record CommentListItemResponse(
        String memberName,
        String contents,
        LocalDateTime createDate
) {
}
