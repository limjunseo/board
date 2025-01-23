package com.jun.board_project.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Comment {
    private String boardId;

    //기준 댓글번호
    private String commentId;

    //seq 일련번호가 1이면 댓글 1이 아니면 대댓글
    private Long commentSeq;

    //pk

    private String content;

    private String userId;


}
