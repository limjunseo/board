package com.jun.board_project.domain.board;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public Board save(Board board) {
        String sql =
                "INSERT INTO board (user_id, board_category_code, board_title, board_created_date) VALUES (?, ?, ?, ?)";


        jdbcTemplate.update(sql, board.getMemberId(), board.getBoardCtId(), board.getBoardTitle(), new java.sql.Timestamp(System.currentTimeMillis()));
        return board;
    }

    public Board update(Board board) {
//        jdbcTemplate.update("UPDATE post SET title = ?, content = ? WHERE postNm = ?",
        return null;
    }

    //max + 1 채번
    public int nextVal() {
        String sql = "select nvl(max(board_id), 1) from board";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    public Board findById(Long boardId) {
        String sql = "select * from board where board_id = ?";
        return jdbcTemplate.queryForObject(sql, new BoardRowMapper(), boardId);
    }

    //특정 카테고리 게시글 조회 페이지별로 10개씩
    public List<Board> findAllByBoardCtId(String boardCtId) {
        String sql = "select * from board where board_category_cd = ?";
        return jdbcTemplate.query(sql, new BoardRowMapper(), boardCtId);
    }


}
