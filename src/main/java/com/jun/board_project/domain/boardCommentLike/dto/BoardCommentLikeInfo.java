package com.jun.board_project.domain.boardCommentLike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter @Setter
public class BoardCommentLikeInfo {
    private int commentId;

    private int commentSeq;
}
