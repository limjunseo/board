package com.jun.board_project.domain.BoardComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter @Builder @Setter
public class BoardCommentReplyForm {
    private int boardId;

    private int commentId;

    private String memberId;

    private String commentContent;

    public BoardComment toBoard() {
        return BoardComment.builder()
                .boardId(boardId)
                .commentId(commentId)
                .memberId(memberId)
                .commentContent(commentContent)
                .build();
    }

}
