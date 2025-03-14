package com.jun.board_project.domain.member.topMember.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthTopMemberInfoRowMapper implements RowMapper<MonthTopMemberInfo> {
    @Override
    public MonthTopMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonthTopMemberInfo monthTopMemberInfo = new MonthTopMemberInfo();
        monthTopMemberInfo.setMemberId(rs.getString("member_id"));
        monthTopMemberInfo.setRanking(rs.getString("rn"));
        monthTopMemberInfo.setSelectedCntInThisMonth(rs.getInt("selected_cnt_in_this_month"));
        return monthTopMemberInfo;
    }
}
