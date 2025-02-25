package com.jun.board_project.domain.board.board;


import lombok.*;

import java.sql.Timestamp;

@ToString
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
