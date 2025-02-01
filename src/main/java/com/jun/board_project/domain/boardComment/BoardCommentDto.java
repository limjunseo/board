package com.jun.board_project.domain.boardComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter @Builder
@AllArgsConstructor
public class BoardCommentDto {
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
