package com.example.backend.service;

import com.example.backend.domain.Board;
import com.example.backend.domain.Member;
import com.example.backend.dto.board.BoardCreateRequest;
import com.example.backend.dto.board.BoardListItemResponse;
import com.example.backend.dto.board.BoardUpdateRequest;
import com.example.backend.exception.BoardNotFoundException;
import com.example.backend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberService memberService;

    public List<Board> findAll(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<BoardListItemResponse> findAllListDTO(){
        return boardRepository.findBoardListDTO();
    }

    // 게시글 조회수 증가
    public void hitCountIncrease(Long id){
        Board board = findByIdOrThrow(id);
        board.setHitCount(board.getHitCount() + 1);
        boardRepository.save(board);
    }

    // 게시글 생성
    public void saveBoard(BoardCreateRequest data, String memberId){
        Member authMember = memberService.findMemberByMemberId(memberId);
        Board board = Board.builder()
                .title(data.getTitle())
                .contents(data.getContents())
                .member(authMember)
                .build();
        boardRepository.save(board);
    }

    // 게시글 수정
    public void updateBoard(Long boardId, BoardUpdateRequest data, String currentLoginId){
        Board board = findByIdOrThrow(boardId);

        validateOwner(board, currentLoginId);

        board.setTitle(data.getTitle());
        board.setContents(data.getContents());
        boardRepository.save(board);
    }

    // 게시글 삭제
    public void deleteBoard(Long boardId, String currentLoginId){
        Board board = findByIdOrThrow(boardId);
        validateOwner(board, currentLoginId);

        boardRepository.delete(board);
    }

    // 게시글 작성자와 현재 접속자와 같은지 비교
    public void validateOwner(Board board, String currentLoginId){
        if(board.getMember() == null || !board.getMember().getMemberId().equals(currentLoginId)) {
            throw new AccessDeniedException("You do not have permission to modify this board");
        }
    }

    // boardId로 게시글 조회
    public Board findByIdOrThrow(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(id));
    }
}
