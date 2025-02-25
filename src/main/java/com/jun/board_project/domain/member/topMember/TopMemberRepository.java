package com.jun.board_project.domain.member.topMember;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class TopMemberRepository {
    private JdbcTemplate jdbcTemplate;

    public void calTopMember() {
        String sql = """
    insert into top_member
    select a.member_id, trunc(sysdate - 1), a.score, a.rn
    from (
        select t.member_id, sum(t.cnt) score, rank() over (order by sum(t.cnt) desc) rn
        from (
            select a.member_id, count(*) cnt -- 유저가 어제 쓴 게시글 개수
            from member a, board b
            where a.member_id = b.member_id
            and b.board_created_dt >= trunc(sysdate - 1)
            and b.board_created_dt < trunc(sysdate)
            group by a.member_id

            union all

            select a.member_id, count(*) cnt -- 유저가 어제 쓴 게시글의 좋아요 수 합계
            from member a, board b, board_like c
            where a.member_id = b.member_id
            and b.board_created_dt >= trunc(sysdate - 1)
            and b.board_created_dt < trunc(sysdate)
            and b.board_id = c.board_id
            group by a.member_id

            union all

            select a.member_id, count(*) cnt -- 유저가 어제 작성한 댓글 수
            from member a, board_comment b
            where a.member_id = b.member_id
            and b.comment_created_dt >= trunc(sysdate - 1)
            and b.comment_created_dt < trunc(sysdate)
            group by a.member_id
        ) t
        group by t.member_id
    ) a
    where rn <= 10;
""";

        jdbcTemplate.update(sql);
    }

    //어제의 우수멤버 조회
    public List<TopMemberInfo> findYesterdayTopMember() {
        String sql = """
        SELECT * 
        FROM TOP_MEMBER
        WHERE CREATED_DT >= TRUNC(SYSDATE - 1)
        """;
        return jdbcTemplate.query(sql, new TopMemberInfoRowMapper());

    }

    //이번달 우수멤버 조회
    public List<MonthTopMemberInfo> findThisMonthTopMember() {
        String sql = """
    SELECT *
    FROM (
        SELECT member_id, 
               count(*) AS selected_cnt_in_this_month, 
               RANK() OVER (ORDER BY count(*) DESC) AS rn
        FROM TOP_MEMBER 
        WHERE created_dt >= TRUNC(SYSDATE, 'MM')
        GROUP BY member_id
    ) t
    WHERE t.rn <= 10
""";

        return jdbcTemplate.query(sql, new MonthTopMemberInfoRowMapper());
    }
}