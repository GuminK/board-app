package com.example.backend.service;

import com.example.backend.domain.Board;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Member;
import com.example.backend.dto.comment.CommentCreateRequest;
import com.example.backend.dto.comment.CommentListItemResponse;
import com.example.backend.dto.comment.CommentUpdateRequest;
import com.example.backend.exception.CommentNotFoundException;
import com.example.backend.repository.CommentRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteComment(Long commentId, String currentLoginId){
        Comment comment = findByIdOrThrow(commentId);
        validateOwner(comment, currentLoginId);

        commentRepository.delete(comment);
    }

    public void updateComment(Long commentId, String currentLoginId, CommentUpdateRequest request){
        Comment comment = findByIdOrThrow(commentId);
        validateOwner(comment, currentLoginId);

        comment.setContents(request.contents());

        commentRepository.save(comment);
    }

    public List<CommentListItemResponse> getCommentList(Long boardId){
        return commentRepository.findCommentListByBoardId(boardId);
    }

    public void validateOwner(Comment comment, String currentLoginId){
        if(comment.getMember() == null || !comment.getMember().getMemberId().equals(currentLoginId)){
            throw new AccessDeniedException("You do not have permission");
        }
    }

    public Comment findByIdOrThrow(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
