package com.jun.board_project.domain.boardBookmark;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @ToString
public class BoardBookmark {
    private String memberId;

    private int boardId;

    //북마크 여부
    private char bookmarkYn;


    void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
