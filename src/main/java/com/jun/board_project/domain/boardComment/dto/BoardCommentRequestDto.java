package com.jun.board_project.domain.boardComment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter @Builder @Setter
public class BoardCommentRequestDto {
    private String commentContent;

}



