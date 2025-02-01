package com.jun.board_project.domain.boardCommentLike;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardCommentLikeRepository {
    private final JdbcTemplate jdbcTemplate;

    //댓글 좋아요 저장하기
    public void saveBoardCommentLike(BoardCommentLike boardCommentLike) {
        String sql =
                "insert into board_comment_like(board_id, comment_id, comment_seq, member_id) "
                        +"values(?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                boardCommentLike.getBoardId(), boardCommentLike.getCommentSeq(), boardCommentLike.getMemberId());
    }

}
