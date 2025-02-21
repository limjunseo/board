package com.jun.board_project.domain.point;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PointRepository {
    private final JdbcTemplate jdbcTemplate;

    //포인트 적립, 모든 포인트 내역은 임시 테이블에 저장 후 일정시간에 포인트 테이블로 이동
    public void savePoint(Point point) {
        String sql = "insert into point_temp(member_id, seq, point_cd, value, pt_base_id, point_dt) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, point.getMemberId(), point.getSeq(), point.getPointCd(), point.getValue(), point.getPtBaseId(), point.getPointDt());
    }

    //max + 1 채번
    public int findSeqByMemberId(String memberId) {
        String sql = "select nvl(max(seq), 0) + 1 from point where member_id = ?";
        return jdbcTemplate.queryForObject(sql, int.class, memberId);
    }

    //00시에 업데이트 완료 후 포인트 테이블로 이동
    public void mergePoint() {
        String sql = """
                INSERT INTO point //포인트 테이블 
                SELECT *
                FROM point_temp //포인트 임시 테이블
                """;
        jdbcTemplate.update(sql);
    }

    //잘못 계산된 포인트 내역들 수정대상 테이블로 이동
    public void savePointModification() {
        String sql = """
                INSERT INTO point_modification
                SELECT *
                FROM point_temp
                """;
        jdbcTemplate.update(sql);
    }

}
