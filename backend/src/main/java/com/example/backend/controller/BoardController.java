package com.example.backend.controller;


import com.example.backend.domain.Board;
import com.example.backend.domain.Member;
import com.example.backend.dto.board.BoardContentDataDTO;
import com.example.backend.dto.board.BoardListDTO;
import com.example.backend.dto.board.BoardResponseDTO;
import com.example.backend.dto.board.BoardUpdateDataDTO;
import com.example.backend.service.BoardService;
import com.example.backend.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    public BoardController(BoardService boardService, MemberService memberService){
        this.boardService = boardService;
        this.memberService = memberService;
    }

    // 게시글 리스트
    @GetMapping("/board/list")
    public ResponseEntity<List<BoardListDTO>> boardList(){
        return ResponseEntity.ok(boardService.findAllListDTO());
    }


    // 특정 게시글
    @GetMapping("/board/detail/{id}")
    public ResponseEntity<BoardResponseDTO> boardByIdSearch(@PathVariable Long id){
        boardService.hitCountIncrease(id);

        Board board = boardService.findById(id);
        if(board == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new BoardResponseDTO(board));
    }

    // 게시글 등록
    @PostMapping("/board/create")
    public String boardCreate(@RequestBody BoardContentDataDTO data, Authentication authentication){

        System.out.println("글 작성자: " + authentication.getName());
        Member authMember = memberService.findMemberByMemberId(authentication.getName());

        System.out.println("authMember: " + authMember);

        boardService.saveBoard(data, authMember);
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
