package com.jun.board_project.domain.boardComment.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardCommentInfoRowMapper implements RowMapper<BoardCommentInfo> {
    @Override
    public BoardCommentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BoardCommentInfo.builder()
                .boardId(rs.getInt("board_id"))
                .commentId(rs.getInt("comment_id"))
                .commentSeq(rs.getInt("comment_seq"))
                .commentContent(rs.getString("comment_content"))
                .memberId(rs.getString("member_id"))
                .commentCreatedDt(rs.getTimestamp("comment_created_dt"))
                .commentLikeCnt(rs.getInt("like_cnt")).build();
    }
}
