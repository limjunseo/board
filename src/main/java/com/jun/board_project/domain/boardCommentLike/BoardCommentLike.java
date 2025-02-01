package com.jun.board_project.domain.boardCommentLike;

import lombok.Builder;
import lombok.Getter;


@Getter @Builder
public class BoardCommentLike {
    private int boardId;

    //기준 댓글번호
    private int commentId;

    //seq 일련번호가 1이면 댓글 1이 아니면 대댓글
    private int commentSeq;

    //pk

    private String memberId;
}
