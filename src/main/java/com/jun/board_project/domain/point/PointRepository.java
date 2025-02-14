package com.jun.board_project.domain.point;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointRepository {
    private final JdbcTemplate jdbcTemplate;

    public void savePoint(Point point) {
        String sql = "insert into point (member_id, point, point_type, description) values(?, ?, ?, ?)";

        jdbcTemplate.update(sql, point.getMemberId(), point.getPoint(), point.getPointType(), point.getDescription());
    }

}
