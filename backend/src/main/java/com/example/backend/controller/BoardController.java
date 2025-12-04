package com.example.backend.controller;


import com.example.backend.domain.Board;
import com.example.backend.dto.BoardContentDataDTO;
import com.example.backend.dto.BoardUpdateDataDTO;
import com.example.backend.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    // 게시글 리스트
    @GetMapping("/board/list")
    public List<Board> boardList(){

        return boardService.findAll();
    }

    
    // 특정 게시글
    @GetMapping("/board/detail/{id}")
    public Board boardByIdSearch(@PathVariable Long id){
        boardService.hitCountIncrease(id);
        return boardService.findById(id);
    }

    // 게시글 등록
    @PostMapping("/board/create")
    public String boardCreate(@RequestBody BoardContentDataDTO data){
        System.out.println(data);
        boardService.saveBoard(data);
        return "success";
    }

    // 게시글 수정
    @PostMapping("/board/update")
    public String boardUpdate(@RequestBody BoardUpdateDataDTO data){
        boardService.saveBoard(data);
        return "success";
    }

    // 게시글 삭제
    @DeleteMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
        return "success";
    }
}
