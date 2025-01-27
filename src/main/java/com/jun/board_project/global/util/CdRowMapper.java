package com.jun.board_project.global.util;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CdRowMapper implements RowMapper<CdDto> {
    @Override
    public CdDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CdDto(rs.getString("cd_id"), rs.getString("cd_name"));
    }
}
