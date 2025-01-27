package com.jun.board_project.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    // findByMemberId
    public Member findByMemberId(String memberId) {
        String sql = "select * from member where member_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), memberId);
        } catch (Exception e) {
            return null;
        }
    }

    //회원존재확인
    public boolean isExistMember(String memberId) {
        String sql = "select count(*) from member where member_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, memberId) > 0;
    }

    // save
    public void save(Member member) {
        String sql = "insert into member(member_id, member_pw, member_name, member_rank) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, member.getMemberId(), member.getMemberPw(),
                member.getMemberName(), MemberRank.defaultRank());
    }

}
