package com.jun.board_project.domain.board.board;

import com.jun.board_project.domain.boardDetail.BoardDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter @Builder
public class BoardForm {
    private String memberId;

    private String boardCtId;

    private String boardTitle;

    private String boardContent;


    public void setDefault() {
        this.boardContent = "여기에 글을 입력하세요";
        this.boardTitle = "여기에 제목을 입력하세요";
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Board toBoard() {
        return Board.builder()
                .memberId(memberId)
                .boardCtId(boardCtId)
                .boardTitle(boardTitle)
                .build();
    }

    public BoardDetail toBoardDetail() {
        return BoardDetail.builder()
                .boardContent(boardContent)
                .build();
    }
}
