package com.jun.board_project.domain.board.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter @Builder
public class BoardInfo {
    private int boardId;

    private String boardCtId;

    private String memberId;

    private String boardTitle;

    private String boardContent;

    private Timestamp boardCreatedDt;

    private int boardLikeCnt; //좋아요 숫자

    private String bookmarkYn; //북마크 여부

    private String likeYn; //좋아요 여부
    
    
    void setLikeYn(String likeYn) {
        this.likeYn = likeYn;
    }

    void setBookmarkYn(String bookmarkYn) {
        this.bookmarkYn = bookmarkYn;
    }
    
}
