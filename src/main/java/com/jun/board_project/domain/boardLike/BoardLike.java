package com.jun.board_project.domain.boardLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class BoardLike {
    private String memberId;

    private int boardId;


}
