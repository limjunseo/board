package com.jun.board_project.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class Sequence {
    private final JdbcTemplate jdbcTemplate;

//    public static Long nextVal() {
//
//    }
}
