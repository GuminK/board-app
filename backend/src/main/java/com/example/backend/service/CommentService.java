package com.example.backend.service;

import com.example.backend.domain.Board;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Member;
import com.example.backend.dto.comment.CommentCreateRequest;
import com.example.backend.dto.comment.CommentListItemResponse;
import com.example.backend.exception.CommentNotFoundException;
import com.example.backend.repository.CommentRepository;
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

        // 부모 댓글 null 확인, 해당 게시글 댓글인지 확인
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

//    public List<CommentListItemResponse> getCommentList(Long boardId){
//        commentRepository.find
//    }

    public List<CommentListItemResponse> getCommentList(Long boardId){
        return commentRepository.findCommentListByBoardId(boardId);
    }

//    public List<Comment> getCommentList(Long boardId){
//
//        System.out.println("boardId에 해당하는 댓글들 ");
//        List<Comment> Test = commentRepository.findByBoard_Id(boardId);
//
//        for (Comment testcomment : Test){
//            System.out.println("댓글 내용: " + testcomment.getContents());
//        }
//        return commentRepository.findByBoard_Id(boardId);
//    }

    public Comment findByIdOrThrow(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
