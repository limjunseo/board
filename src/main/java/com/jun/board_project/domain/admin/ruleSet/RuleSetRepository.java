package com.jun.board_project.domain.admin.ruleSet;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RuleSetRepository {
    private final JdbcTemplate  jdbcTemplate;

    public int findTargetValue(int ptBaseId, int functionId, int cellId, String targetDate) {
        String sql = """
            select target_value
            from rule_set
            where pt_base_id = ?
            and function_id = ?
            and cell_id = ?
            and start_dt <= ?
            and end_dt >= ?
        """;
        return jdbcTemplate.queryForObject(sql, int.class, ptBaseId, functionId, cellId, targetDate, targetDate);
    }
}
