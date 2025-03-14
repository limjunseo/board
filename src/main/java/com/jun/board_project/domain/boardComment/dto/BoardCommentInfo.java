package com.jun.board_project.domain.boardComment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter @Builder
@AllArgsConstructor @ToString
public class BoardCommentInfo {
    private int boardId;

    //기준 댓글번호
    private int commentId;

    //seq 일련번호가 1이면 댓글 1이 아니면 대댓글
    private int commentSeq;

    //pk

    private String commentContent;

    private String memberId;

    private Timestamp commentCreatedDt;

    private int commentLikeCnt;

    boolean isLike;

    public void setLiked(boolean isLike) {
        this.isLike = isLike;
    }

}
