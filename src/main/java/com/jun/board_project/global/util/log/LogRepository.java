package com.jun.board_project.global.util.log;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class LogRepository {
    private final JdbcTemplate  jdbcTemplate;

    public void insertUpdateCompleteLog(UpdateLogInfo updateLogInfo) {
        String sql = """
    
    INSERT INTO UPDATE_LOG (UPDATE_LOG_ID, UPDATE_DT) VALUES (?, ?)
    
    """;
        jdbcTemplate.update(sql, updateLogInfo.getUpdateLogId(), updateLogInfo.getUpdateDt());
    }

    public int nextVal() {
        String sql = """
    
    SELECT NVL(MAX(update_log_id), 0) + 1
    FROM update_log
    
    """;
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    //마지막 업데이트 시간
    public LocalDateTime findLastUpdateDt() {
        String sql = "select max(update_dt) from update_log";
        return jdbcTemplate.queryForObject(sql, LocalDateTime.class);
    }
}
