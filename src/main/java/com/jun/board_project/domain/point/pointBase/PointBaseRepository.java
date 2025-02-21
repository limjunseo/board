package com.jun.board_project.domain.point.pointBase;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointBaseRepository {
    private final JdbcTemplate jdbcTemplate;
}
