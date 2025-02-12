package com.jun.board_project.domain.job;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class TopMemberHisRepository {
    private JdbcTemplate jdbcTemplate;

    public void saveTopMember() {
        String sql = """
    insert into top_member_his
    select a.member_id, to_char(trunc(sysdate - 1), 'yyyymmdd'), a.score, a.rn
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

}
