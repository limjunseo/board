package com.jun.board_project.domain.boardLike;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardLikeRepository {
    private final JdbcTemplate jdbcTemplate;

    public void saveBoardLike(BoardLike boardLike) {
        String sql = "insert into board_like(board_id, member_id) values(?, ?)";
        jdbcTemplate.update(sql, boardLike.getBoardId(), boardLike.getMemberId());
    }

    
    //게시글 좋아요 여부
    //반환값이 없을 경우 'N'으로 반환
    public String findLikeYn(int boardId, String memberId) {
        String sql =
                """
                select case when exists 
                (
                    select '1'
                    from board_like
                    where board_id = ?
                    and member_id = ?
                ) then 'Y' else 'N' end like_yn
                from dual
                """;


        return jdbcTemplate.queryForObject(sql, String.class, boardId, memberId);
    }
}