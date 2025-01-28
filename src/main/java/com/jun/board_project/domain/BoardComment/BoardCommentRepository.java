package com.jun.board_project.domain.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<BoardComment> findCommentByBoardId(int boardId) {
        String sql =
                "select * from board_comment "
                +"where board_id = ?";

        return jdbcTemplate.query(sql, new BoardCommentRowMapper(), boardId);
    }

    public int saveComment(BoardComment boardComment) {
        String sql =
                "insert into board_comment(board_id, board_id, comment_id, comment_seq, comment_content, member_id "
                +"values (? ? ? ? ? ?)";


        return 0;
    }


}
