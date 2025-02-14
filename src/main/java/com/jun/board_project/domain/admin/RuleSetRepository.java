package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Repository
public class RuleSetRepository {
    private final JdbcTemplate  jdbcTemplate;

    public int findTargetValue(int ptBaseId, int functionId, int cellId, Timestamp targetDate) {
        String sql = """
            select target_value
            from rule_set
            where pt_base_id = ?
            and function_id = ?
            and cell_id = ?
            and start_date <= ?
            and end_date >= ?
        """;
        return jdbcTemplate.queryForObject(sql, int.class, ptBaseId, functionId, cellId, targetDate, targetDate);
    }
}
