package com.jun.board_project.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter @Builder
public class BoardDto {
    private int boardId;

    private String boardCtId;

    private String memberId;

    private String boardTitle;

    private String boardContent;

    private Timestamp boardCreatedDt;
}
