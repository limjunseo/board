package com.jun.board_project.domain.board;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BoardCtCdRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<BoardCtDto> findById(String cdTypeId) {
        String sql = "SELECT * FROM cd WHERE cd_type_id = ?";
        return jdbcTemplate.query(sql,  new Object[]{cdTypeId}, (rs, rowNum) -> {
            BoardCtDto boardCtDto = new BoardCtDto();
            boardCtDto.setBoardCtId(rs.getString("cd_id"));
            boardCtDto.setBoardCtName(rs.getString("cd_name"));
            return boardCtDto;
        });
    }

    public BoardCtDto findByCtCd(String ctCd) {
        String sql = "SELECT * FROM cd WHERE cd_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ctCd}, (rs, rowNum) -> {
            BoardCtDto boardCtDto = new BoardCtDto();
            boardCtDto.setBoardCtId(rs.getString("cd_id"));
            boardCtDto.setBoardCtName(rs.getString("cd_name"));
            return boardCtDto;
        });
    }
}
