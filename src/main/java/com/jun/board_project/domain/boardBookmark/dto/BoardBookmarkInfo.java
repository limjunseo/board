package com.jun.board_project.domain.boardBookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
public class BoardBookmarkInfo {
    private int boardId;

    private String memberId;

    private String boardCtId;

    private String boardTitle;

    private Timestamp boardCreatedDt;

}
