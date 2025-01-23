package com.jun.board_project.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(User user) {

    }
}
