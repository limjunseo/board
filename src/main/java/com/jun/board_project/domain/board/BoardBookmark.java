package com.jun.board_project.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class BoardBookmark {
    private Long userId;

    private Long boardId;

    //북마크 여부
    private char bookmarkYn;
}
