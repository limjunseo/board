package com.jun.board_project.domain.BoardComment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardCommentRowMapper implements RowMapper<BoardComment> {
    @Override
    public BoardComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BoardComment.builder()
                .boardId(rs.getInt("board_id"))
                .commentId(rs.getInt("board_comment_id"))
                .commentSeq(rs.getInt("comment_seq"))
                .commentContent(rs.getString("comment_content"))
                .memberId(rs.getString("member_id"))
                .commentCreatedDt(rs.getTimestamp("comment_created_dt")).build();
    }
}
