package com.jun.board_project.domain.board;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class BoardForm {
    private String userId;

    private String boardCategoryCode;

    private String boardTitle;

    private String boardContent;

    public Board toBoard() {
        return Board.builder()
                .userId(userId)
                .boardCategoryCode(boardCategoryCode)
                .boardTitle(boardTitle)
                .build();
    }

    public BoardDetail toBoardDetail() {
        return BoardDetail.builder()
                .boardContent(boardContent)
                .build();
    }
}
