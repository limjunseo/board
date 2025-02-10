package com.jun.board_project.domain.member;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class TopMemberHisRepository {
    private JdbcTemplate jdbcTemplate;


}
