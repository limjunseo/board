package com.jun.board_project.domain.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCommentLikeRepository {
    private final JdbcTemplate jdbcTemplate;


}
