package com.jun.board_project.domain.boardCommentLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter @Setter
public class BoardCommentLikeDto {
    private int commentId;

    private int commentSeq;
}
