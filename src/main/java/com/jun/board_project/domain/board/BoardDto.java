package com.jun.board_project.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardDto {
    private String userId;

    private String boardCategoryCode;

    private String boardTitle;

    private String boardContent;

    private String boardCreatedDate;
}
