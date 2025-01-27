package com.jun.board_project.domain.board;

import com.jun.board_project.domain.boardDetail.BoardDetail;
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
                .boardCtId(boardCategoryCode)
                .boardTitle(boardTitle)
                .build();
    }

    public BoardDetail toBoardDetail() {
        return BoardDetail.builder()
                .boardContent(boardContent)
                .build();
    }
}
