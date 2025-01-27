package com.jun.board_project.domain.boardBookmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class BoardBookmark {
    private String memberId;

    private int boardId;

    //북마크 여부
    private char bookmarkYn;
}
