package com.jun.board_project.domain.board;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDtoRowMapper implements RowMapper<BoardDto> {
    @Override
    public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BoardDto.builder()
                .boardId(rs.getInt("board_id"))
                .boardTitle(rs.getString("board_title"))
                .boardContent(rs.getString("board_content"))
                .boardCreatedDt(rs.getTimestamp("board_created_dt"))
                .boardCtId(rs.getString("board_ct_id"))
                .boardLikeCnt(rs.getInt("like_cnt"))
                .build();
    }
}
