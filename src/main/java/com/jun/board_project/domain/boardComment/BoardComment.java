package com.jun.board_project.domain.boardComment;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @ToString
public class BoardComment {
    private int boardId;

    //기준 댓글번호
    private int commentId;

    //seq 일련번호가 1이면 댓글 1이 아니면 대댓글
    private int commentSeq;

    //pk

    private String commentContent;

    private String memberId;

    private Timestamp commentCreatedDt;


    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setDefaultCommentSeq() {
        this.commentSeq = 1;
    }

    public void setCommentSeq(int commentSeq) {
        this.commentSeq = commentSeq;
    }


}
