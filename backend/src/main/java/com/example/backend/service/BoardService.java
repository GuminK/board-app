package com.example.backend.service;

import com.example.backend.domain.Board;
import com.example.backend.dto.BoardContentDataDTO;
import com.example.backend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public List<Board> findAll(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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

    public void saveBoard(BoardContentDataDTO data){
        Board board = new Board(data.getTitle(),data.getContents());
        boardRepository.save(board);
    }
}
