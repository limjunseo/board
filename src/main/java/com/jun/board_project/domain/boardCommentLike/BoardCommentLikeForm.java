package com.jun.board_project.domain.boardCommentLike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardCommentLikeForm {
    private int boardId;

    private int commentId;

    private int commentSeq;

}
