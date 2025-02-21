package com.jun.board_project.domain.point.point;

import com.jun.board_project.domain.point.pointBase.PointBaseCd;
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
    
    INSERT INTO POINT_MODIFICATION 
    SELECT pt.*
    FROM (
        SELECT max(update_dt) recent_update_dt -- 업데이트 완료시간 
        FROM UPDATE_LOG 
    ) ul, POINT_TEMP pt
    WHERE pt.point_dt <= ul.recent_update_dt
    """;

        jdbcTemplate.update(sql);
    }

    //잘못 계산된 포인트 내역 수정
    public void modifyPoint() {
        String sql = """
    
    UPDATE (
        SELECT pm.value AS point_value, rs.target_value AS new_point_value
        FROM POINT_MODIFICATION pm
        JOIN PT_BASE pb ON pm.pt_base_id = pb.pt_base_id
        JOIN RULE_SET rs ON rs.pt_base_id = pb.pt_base_id
            AND rs.function_id = pb.function_id
        WHERE pm.point_dt BETWEEN rs.start_dt AND rs.end_dt
        AND rs.target_value <> pm.value
    )
    SET point_value = new_point_value
        
    """;

            jdbcTemplate.update(sql);
    }

    //다시 포인트 임시 테이블에 병합
    public void mergeIntoPointTemp(){
        String sql = """
    
    MERGE INTO POINT_TEMP pt 
    USING POINT_MODIFICATION pm
    ON (pt.member_id = pm.member_id
        AND pt.seq = pm.seq)
    WHEN MATCHED THEN 
    UPDATE SET pt.value = pm.value
    WHERE pt.value <> pm.value
    
    """;


        jdbcTemplate.update(sql);
    }

}
