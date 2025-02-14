package com.jun.board_project.domain.point;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointRepository {
    private final JdbcTemplate jdbcTemplate;

    //포인트 적립
    public void savePoint(Point point) {
        String sql = "insert into point(member_id, seq, point_cd, value) values(?, ?, ?)";
        jdbcTemplate.update(sql, point.getMemberId(), point.getSeq(), point.getPointCd(), point.getValue());
    }

    //max + 1 채번
    public int findSeqByMemberId(String memberId) {
        String sql = "select nvl(max(seq), 0) + 1 from point where member_id = ?";
        return jdbcTemplate.queryForObject(sql, int.class, memberId);
    }

}
