package com.jun.board_project.domain.board;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<Board> {
    @Override
    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
        Board board = Board.builder()
                .boardId(rs.getInt("board_id"))
                .userId(rs.getString("user_id"))
                .boardCategoryCode(rs.getString("board_category_code"))
                .boardTitle(rs.getString("board_title"))
                .build();
        return board;
    }
}
