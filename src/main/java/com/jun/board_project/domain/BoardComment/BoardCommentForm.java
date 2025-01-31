package com.jun.board_project.domain.BoardComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter @Builder @Setter
public class BoardCommentForm {
    private int boardId;

    private String memberId;

    private String commentContent;

    public BoardComment toBoard() {
        return BoardComment.builder()
                .boardId(boardId)
                .memberId(memberId)
                .commentContent(commentContent)
                .build();
    }

}
