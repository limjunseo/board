package com.jun.board_project.domain.boardCommentLike.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardCommentLikeReqeustDto {
    private int commentId;

    private int commentSeq;

}
