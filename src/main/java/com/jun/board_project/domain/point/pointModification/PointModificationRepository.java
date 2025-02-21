package com.jun.board_project.domain.point.pointModification;

import com.jun.board_project.domain.point.point.PointCd;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PointModificationRepository {
    private final JdbcTemplate jdbcTemplate;

    public void modifyPoint(String memberId, int seq, int targetValue) {
        String sql = """
    
    UPDATE POINT_MODIFICATION 
    SET value = ?
    WHERE member_id = ?
    AND seq = ?
    
    """;
        jdbcTemplate.update(sql, targetValue, memberId, seq);
    }

    public List<PointModificationInfo> findPointModificationInfo(LocalDateTime updateTime) {

        String sql = """
    SELECT *
    FROM POINT_MODIFICATION pm 
    WHERE POINT_DT <= ?
    """;

            return jdbcTemplate.query(sql, new Object[]{updateTime}, (rs, rowNum) ->
                    PointModificationInfo.builder()
                            .seq(rs.getInt("seq"))
                            .memberId(rs.getString("member_id"))
                            .value(rs.getInt("value"))
                            .pointDt(LocalTime.from(rs.getTimestamp("point_dt").toLocalDateTime()))
                            .pointCd(PointCd.valueOf(rs.getString("point_cd"))) //지출지급소멸구분코드
                            .build()
            );
    }
}
