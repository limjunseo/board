package com.jun.board_project.domain.memberLoginHis;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberLoginHisRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(MemberLoginHis memberLoginHis) {
        String sql = "insert into member_login_his(member_id, login_date) values(?, ?)";
        jdbcTemplate.update(sql, memberLoginHis.getMemberId(), memberLoginHis.getLoginDate());
    }

    //금일 최초 로그인 여부 'Y', 'N' 반환
    public String isFirstLogin(String memberId) {
        String sql = """
    
    select (case when exists 
            (select '1'
                from member_login_his
                where member_id = ?
                and login_date >= trunc(sysdate)) then 'y' else 'n' end )
    
    """;
        return jdbcTemplate.queryForObject(sql, String.class, memberId);
    }
}
