package com.jun.board_project.domain.boardComment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardCommentDtoRowMapper implements RowMapper<BoardCommentDto> {
    @Override
    public BoardCommentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BoardCommentDto.builder()
                .boardId(rs.getInt("board_id"))
                .commentId(rs.getInt("comment_id"))
                .commentSeq(rs.getInt("comment_seq"))
                .commentContent(rs.getString("comment_content"))
                .memberId(rs.getString("member_id"))
                .commentCreatedDt(rs.getTimestamp("comment_created_dt"))
                .boardCommentLikeCnt(rs.getInt("like_cnt")).build();
    }
}
