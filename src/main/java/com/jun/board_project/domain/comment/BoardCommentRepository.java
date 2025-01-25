package com.jun.board_project.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCommentRepository {
    private final JdbcTemplate jdbcTemplate;



}
