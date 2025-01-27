package com.jun.board_project.domain.board;

import com.jun.board_project.domain.boardDetail.BoardDetail;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class BoardForm {
    private String memberId;

    private String boardCategoryCode;

    private String boardTitle;

    private String boardContent;

    public void setDefault() {
        this.boardContent = "여기에 글을 입력하세요";
        this.boardTitle = "여기에 제목을 입력하세요";
    }

    public Board toBoard() {
        return Board.builder()
                .memberId(memberId)
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
