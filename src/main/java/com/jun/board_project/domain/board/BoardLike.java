package com.jun.board_project.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardLike {
    private String userId;

    private Long boardId;


}
