package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RuleMatrixRepository {
    private final JdbcTemplate jdbcTemplate;

    public int findCellId(int functionId, String [] dimens) {
        String sql = """
                select cell_id 
                from rule_matrix 
                where function_id = ?
                and dimen1_value = ?
                and dimen2_value = ?
                and dimen3_value = ?
                and dimen4_value = ?""";

        return jdbcTemplate.queryForObject(sql, int.class, functionId, dimens[0], dimens[1], dimens[2], dimens[3]);

    }
}
