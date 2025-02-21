package com.jun.board_project.global.util.cd;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@AllArgsConstructor
@Repository
public class CdRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<CdDto> findById(String cdTypeId) {
        String sql = "SELECT * FROM cd WHERE cd_type_id = ?";
        return jdbcTemplate.query(sql, new Object[]{cdTypeId}, (rs, rowNum) -> {
            CdDto cdDto = new CdDto();
            cdDto.setCdId(rs.getString("cd_id"));
            cdDto.setCdName(rs.getString("cd_name"));
            return cdDto;
        });

    }


}
