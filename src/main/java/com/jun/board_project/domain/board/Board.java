package com.jun.board_project.domain.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Board{

        private int boardId;

        private String memberId;

        private String boardCtId;

        private String boardTitle;

        private Timestamp boardCreatedDt;



}
