package com.jun.board_project.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class BoardDetail {
    private Long boardId;
    private String boardContent;
    private String boardImage;

}
