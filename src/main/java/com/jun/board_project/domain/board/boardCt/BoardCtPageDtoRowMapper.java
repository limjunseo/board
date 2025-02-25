package com.jun.board_project.domain.board.boardCt;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardCtPageDtoRowMapper implements RowMapper<BoardCtPageDto> {
    @Override
    public BoardCtPageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        BoardCtPageDto boardCtPageDto = BoardCtPageDto.builder()
                .boardId(rs.getInt("board_id"))
                .memberId(rs.getString("member_id"))
                .boardCtId(rs.getString("board_ct_id"))
                .boardTitle(rs.getString("board_title"))
                .boardCreatedDt(rs.getTimestamp("board_created_dt"))
                .boardLikeCnt(rs.getInt("board_like_cnt"))
                .build();
        return boardCtPageDto;
    }
}
