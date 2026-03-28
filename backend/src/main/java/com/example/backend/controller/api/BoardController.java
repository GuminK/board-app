package com.example.backend.controller.api;


import com.example.backend.dto.board.BoardCreateRequest;
import com.example.backend.dto.board.BoardListItemResponse;
import com.example.backend.dto.board.BoardDetailResponse;
import com.example.backend.dto.board.BoardUpdateRequest;
import com.example.backend.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardListItemResponse>> getBoardList(){
        return ResponseEntity.ok(boardService.findAllListDTO());
    }


    // 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailResponse> getBoardDetail(@PathVariable Long id){
        return ResponseEntity.ok(new BoardDetailResponse(boardService.findByIdOrThrow(id)));
    }

    @PostMapping("/{id}/hit")
    public ResponseEntity<?> increaseBoardHit(@PathVariable Long id){
        boardService.hitCountIncrease(id);
        return ResponseEntity.ok(Map.of("message", "increase hitCount success"));
    }
    
    // 게시글 등록
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardCreateRequest data, Authentication authentication){
        boardService.saveBoard(data, authentication.getName());
        return ResponseEntity.status(201).body(Map.of("message", "create board success"));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequest data, Authentication authentication){
        boardService.updateBoard(id, data, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "update board success"));
    }
    
    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id, Authentication authentication){
        boardService.deleteBoard(id, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "delete board success"));
    }
}
