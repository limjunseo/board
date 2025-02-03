package com.jun.board_project.domain.boardComment;

import com.jun.board_project.domain.boardCommentLike.BoardCommentLike;
import com.jun.board_project.domain.boardCommentLike.BoardCommentLikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;
    private final com.jun.board_project.domain.boardCommentLike.BoardCommentLikeRepository boardCommentLikeRepository;

    @Transactional
    public int saveComment(BoardComment boardComment) {
        int commentId = boardCommentRepository.findCommentIdByBoardId(boardComment.getBoardId());
        boardComment.setCommentId(commentId);
        boardComment.setDefaultCommentSeq(); //기본 댓글은 commetSeq = 1 설정, 대댓글은 commentSeq >= 2
        return boardCommentRepository.saveComment(boardComment);
    }

    @Transactional
    public int saveCommentRe(BoardComment boardComment) {
        int commentSeq = boardCommentRepository.findCommentSeqByBoardIdAndCommentId(
                boardComment.getBoardId(), boardComment.getCommentId());

        boardComment.setCommentSeq(commentSeq);
        return boardCommentRepository.saveComment(boardComment);
    }

    public List<BoardCommentDto> findCommentByBoardId(int boardId) {
        return boardCommentRepository.findCommentByBoardId(boardId);
    }


    //댓글 좋아요 저장
    public void saveBoardCommentLike(BoardCommentLike boardCommentLike){
        boardCommentLikeRepository.saveBoardCommentLike(boardCommentLike);
    }
    
    //member가 특정 board에서 좋아요 누른 댓글들 가져오기
    public List<BoardCommentLikeDto> findLikedBoardCommentByBoardIdAndMemberId(int boardId, String memberId) {
        return boardCommentLikeRepository.findLikedBoardComment(boardId, memberId);
    }




}
