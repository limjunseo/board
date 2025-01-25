package com.jun.board_project.domain.boardDetail;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardDetailRepository {
    private final JdbcTemplate jdbcTemplate;

    // 게시글 상세정보 저장
    public void save(BoardDetail boardDetail) {
        String sql = "insert into board_detail(board_id, content) values(?, ?)";
        jdbcTemplate.update(sql, boardDetail.getBoardId(), boardDetail.getBoardContent());
    }
}
