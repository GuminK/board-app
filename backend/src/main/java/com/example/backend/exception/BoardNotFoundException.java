package com.example.backend.exception;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(Long id){
        super("Board not found. id : " + id);
    }
}
