package com.example.backend.controller.api;


import com.example.backend.domain.Board;
import com.example.backend.domain.Member;
import com.example.backend.dto.board.BoardCreateRequest;
import com.example.backend.dto.board.BoardListItemResponse;
import com.example.backend.dto.board.BoardDetailResponse;
import com.example.backend.dto.board.BoardUpdateRequest;
import com.example.backend.service.BoardService;
import com.example.backend.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    public BoardController(BoardService boardService, MemberService memberService){
        this.boardService = boardService;
        this.memberService = memberService;
    }

    // 게시글 리스트
    @GetMapping("/list")
    public ResponseEntity<List<BoardListItemResponse>> boardList(){
        return ResponseEntity.ok(boardService.findAllListDTO());
    }


    // 특정 게시글
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardDetailResponse> boardByIdSearch(@PathVariable Long id){
        boardService.hitCountIncrease(id);

        Board board = boardService.findById(id);
        if(board == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new BoardDetailResponse(board));
    }

    // 게시글 등록
    @PostMapping("/create")
    public ResponseEntity<?> boardCreate(@RequestBody BoardCreateRequest data, Authentication authentication){
        Member authMember = memberService.findMemberByMemberId(authentication.getName());
        boardService.saveBoard(data, authMember);
        return ResponseEntity.status(201).body(Map.of("message", "success"));
    }

    // 게시글 수정
    @PutMapping("/update")
    public ResponseEntity<?> boardUpdate(@RequestBody BoardUpdateRequest data, Authentication authentication){
        boardService.updateBoard(data, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "success"));
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id, Authentication authentication){
        boardService.deleteBoard(id, authentication.getName());
        return ResponseEntity.ok(Map.of("message", "success"));
    }
}
