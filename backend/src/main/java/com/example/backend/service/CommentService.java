package com.example.backend.service;

import com.example.backend.domain.Board;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Member;
import com.example.backend.dto.comment.CommentCreateRequest;
import com.example.backend.exception.CommentNotFoundException;
import com.example.backend.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final MemberService memberService;

    public CommentService(CommentRepository commentRepository, BoardService boardService, MemberService memberService){
        this.commentRepository = commentRepository;
        this.boardService = boardService;
        this.memberService = memberService;
    }

    public void saveComment(Long boardId, String memberId, CommentCreateRequest request){
        Board board = boardService.findByIdOrThrow(boardId);
        Member member = memberService.findMemberByMemberId(memberId);

        Comment parentComment = null;

        if(request.parentCommentId() != null){
            parentComment = findByIdOrThrow(request.parentCommentId());

            if(!parentComment.getBoard().getId().equals(boardId)){
                throw new IllegalArgumentException("Parent comment does not belong to this board");
            }
        }

        Comment comment = Comment.builder()
                .board(board)
                .member(member)
                .contents(request.contents())
                .parentComment(parentComment)
                .build();

        commentRepository.save(comment);
    }

    public Comment findByIdOrThrow(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
