package com.example.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(Map.of(
                "message", e.getMessage()
        ));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthentication(AuthenticationException e){
        return ResponseEntity.status(401).body(Map.of(
                "message", "Unauthorized"
        ));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException e){
        return ResponseEntity.status(403).body(Map.of(
                "message", "Forbidden"
        ));
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<?> handleBoardNotFound(BoardNotFoundException e){
        return ResponseEntity.status(404).body(Map.of(
                "message", e.getMessage()
        ));
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> handleCommentNotFound(CommentNotFoundException e){
        return ResponseEntity.status(404).body(Map.of(
                "message", e.getMessage()
        ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.status(500).body(Map.of(
                "message", "Internal server error"
        ));
    }
}
