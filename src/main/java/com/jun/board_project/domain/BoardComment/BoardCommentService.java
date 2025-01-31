package com.jun.board_project.domain.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

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

    public List<BoardComment> findCommentByBoardId(int boardId) {
        return boardCommentRepository.findCommentByBoardId(boardId);
    }



}
