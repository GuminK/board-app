package com.example.backend.controller;


import com.example.backend.domain.Board;
import com.example.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HeaderController {

    private final BoardService boardService;

    public HeaderController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/boardList")
    public List<Board> boardList(){

        return boardService.findAll();
    }

    @GetMapping("/board/{id}")
    public Board boardById(@PathVariable Long id){
        return boardService.findById(id);
    }
}
