package com.jun.board_project.domain.board.boardCt;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BoardCtIdRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<BoardCtDto> findByCdTypeId(String cdTypeId) {
        String sql = "select * from cd where cd_type_id = ?";
        return jdbcTemplate.query(sql,  new Object[]{cdTypeId}, (rs, rowNum) -> {
            BoardCtDto boardCtDto = new BoardCtDto();
            boardCtDto.setBoardCtId(rs.getString("cd_id"));
            boardCtDto.setBoardCtName(rs.getString("cd_name"));
            return boardCtDto;
        });
    }


    public BoardCtDto findByCtId(String ctId) {
        String sql = "select * from cd where cd_id = ? and cd_type_id = '001'";
        return jdbcTemplate.queryForObject(sql, new Object[]{ctId},
                (rs, rowNum) -> {
            BoardCtDto boardCtDto = new BoardCtDto();
            boardCtDto.setBoardCtId(rs.getString("cd_id"));
            boardCtDto.setBoardCtName(rs.getString("cd_name"));
            return boardCtDto;
        });
    }
}
