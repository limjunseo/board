package com.jun.board_project.domain.member.member;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getString("member_id"));
        member.setMemberPw(rs.getString("member_pw"));
        member.setMemberName(rs.getString("member_name"));
        member.setMemberRank(rs.getString("member_rank"));
        member.setNewYn(rs.getString("new_yn"));
        member.setMembershipYn(rs.getString("membership_yn"));
        member.setSeqloginYn(rs.getString("seqlogin_yn"));
        return member;
    }
}
