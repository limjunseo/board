package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    public void saveCodeMeta(CodeMetaForm codeMetaForm) {
        String sql = "insert into code_meta(code_meta_id, code_meta_name) values(?, ?)";
        jdbcTemplate.update(sql, codeMetaForm.getCodeMetaId(), codeMetaForm.getCodeMetaName());
    }


}
