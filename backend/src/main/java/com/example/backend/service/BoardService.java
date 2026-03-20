package com.example.backend.service;

import com.example.backend.domain.Board;
import com.example.backend.domain.Member;
import com.example.backend.dto.board.BoardCreateRequest;
import com.example.backend.dto.board.BoardListItemResponse;
import com.example.backend.dto.board.BoardUpdateRequest;
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

    public List<Board> findAll(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<BoardListItemResponse> findAllListDTO(){
        return boardRepository.findBoardListDTO();
    }

    public Board findById(Long id){
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }



    public void hitCountIncrease(Long id){
        Optional<Board> optionalBoard = boardRepository.findById(id);
        Board board =  optionalBoard.orElse(null);
        if(board != null){
            board.setHitCount(board.getHitCount()+1);
            boardRepository.save(board);
        }
    }

    public void saveBoard(BoardCreateRequest data, Member authMember){
//        Board board = new Board(data.getTitle(),data.getContents());
        Board board = Board.builder()
                .title(data.getTitle())
                .contents(data.getContents())
                .member(authMember)
                .build();
        boardRepository.save(board);
    }


    public void updateBoard(BoardUpdateRequest data, String currentMemberId){
        Board board = findByIdOrThrow(data.getId());

        validateOwner(board, currentMemberId);

        board.setTitle(data.getTitle());
        board.setContents(data.getContents());
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId, String currentMemberId){
        Board board = findByIdOrThrow(boardId);
        validateOwner(board, currentMemberId);

        boardRepository.delete(board);
    }

    public void validateOwner(Board board, String currentMemberId){
        if(board.getMember() == null || !board.getMember().getMemberId().equals(currentMemberId)) {
            throw new AccessDeniedException("You do not have permission to modify this board");
        }
    }

    public Board findByIdOrThrow(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found. id=" + id));
    }
}
