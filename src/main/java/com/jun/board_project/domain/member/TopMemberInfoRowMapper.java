package com.jun.board_project.domain.member;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopMemberInfoRowMapper implements RowMapper<TopMemberInfo> {
    @Override
    public TopMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        TopMemberInfo topMemberInfo = new TopMemberInfo();
        topMemberInfo.setMemberId(rs.getString("member_id"));
        topMemberInfo.setMemberRank(rs.getString("member_rank"));
        return topMemberInfo;
    }
}
