package com.jun.board_project.global.util;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@AllArgsConstructor
@Repository
public class CdRepository {
    private final JdbcTemplate jdbcTemplate;

    public CdDto findById(String cdId) {
        String sql = "select * from cd where cd_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cdId}, (rs, rowNum) -> {
            CdDto cdDto = new CdDto();
            cdDto.setCdId(rs.getString("cd_id"));
            cdDto.setCdName(rs.getString("cd_name"));
            return cdDto;
        });
    }

    public List<CdDto> findAll() {
        String sql = "select * from cd";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CdDto cdDto = new CdDto();
            cdDto.setCdId(rs.getString("cd_id"));
            cdDto.setCdName(rs.getString("cd_name"));
            return cdDto;
        });
    }
}
