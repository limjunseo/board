package com.jun.board_project.domain.board.boardCt;


import com.jun.board_project.domain.board.board.dto.BoardCtInfo;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BoardCtIdRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<BoardCtInfo> findByCdTypeId(String cdTypeId) {
        String sql = "select * from cd where cd_type_id = ?";
        return jdbcTemplate.query(sql,  new Object[]{cdTypeId}, (rs, rowNum) -> {
            BoardCtInfo boardCtInfo = new BoardCtInfo();
            boardCtInfo.setBoardCtId(rs.getString("cd_id"));
            boardCtInfo.setBoardCtName(rs.getString("cd_name"));
            return boardCtInfo;
        });
    }


    public BoardCtInfo findByCtId(String ctId) {
        String sql = "select * from cd where cd_id = ? and cd_type_id = '001'";
        return jdbcTemplate.queryForObject(sql, new Object[]{ctId},
                (rs, rowNum) -> {
            BoardCtInfo boardCtInfo = new BoardCtInfo();
            boardCtInfo.setBoardCtId(rs.getString("cd_id"));
            boardCtInfo.setBoardCtName(rs.getString("cd_name"));
            return boardCtInfo;
        });
    }
}
