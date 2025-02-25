package com.jun.board_project.domain.board.boardCt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
public class BoardCtPageDto {
    private int boardId;

    private String memberId;

    private String boardCtId;

    private String boardTitle;

    private int boardLikeCnt;

    private Timestamp boardCreatedDt;
}
