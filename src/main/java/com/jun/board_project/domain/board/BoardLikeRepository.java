package com.jun.board_project.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardLikeRepository {
    private final JdbcTemplate jdbcTemplate;

    public void insert(BoardLike boardLike) {
        String sql = "insert into board_like(board_id, user_id) values(?, ?)";
    }
}