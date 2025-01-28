package com.jun.board_project.domain.BoardComment;

public class BoardCommentForm {
    private int boardId;

    private int commentId;

    private int commentSeq;

    private String memberId;

    private String commentContent;

    public BoardComment toBoard() {
        return BoardComment.builder()
                .boardId(boardId)
                .commentId(commentId)
                .commentSeq(commentSeq)
                .memberId(memberId)
                .commentContent(commentContent)
                .build();
    }

}
