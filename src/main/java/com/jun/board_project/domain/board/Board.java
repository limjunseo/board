package com.jun.board_project.domain.board;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Board{

        private int boardId;

        private String userId;

        private String boardCategoryCode;

        private String boardTitle;


}
