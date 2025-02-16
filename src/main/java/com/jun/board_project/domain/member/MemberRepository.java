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


    //멤버 연속 출석여부 update
    public void updateSeqloginYn() {
        String sql = """
        UPDATE MEMBER m
        SET m.seqlogin_yn = 'Y'
        WHERE m.seqlogin_yn <> 'Y'
        AND EXISTS (
            SELECT '1'
            FROM MEMBER_LOGIN_HIS mlh
            WHERE m.member_id = mlh.member_id
            AND mlh.login_date >= TRUNC(SYSDATE - 1)
    );
""";
        jdbcTemplate.update(sql);
    }

    //멤버 신규여부 update, 가입일 7일이 지난 회원은 'N'으로 변경
    public void updateNewYn() {
        String sql = """
        UPDATE MEMBER m
        SET m.new_yn = 'N'
        WHERE m.new_yn <> 'N'
        AND m.member_created_dt < TO_CHAR(SYSDATE - 7, 'YYYYMMDD')""";

        jdbcTemplate.update(sql);
    }

}
