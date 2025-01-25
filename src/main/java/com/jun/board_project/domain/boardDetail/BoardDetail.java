package com.jun.board_project.domain.boardDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class BoardDetail {
    private int boardId;
    private String boardContent;
    private String boardImage;

}
