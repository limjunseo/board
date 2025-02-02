package com.jun.board_project.domain.boardBookmark;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @ToString @Setter
public class BoardBookmark {
    private String memberId;

    private String boardCtId;

    private int boardId;

    //북마크 여부
    private String bookmarkYn;


    void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
