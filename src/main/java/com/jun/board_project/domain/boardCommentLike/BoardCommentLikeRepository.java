package com.jun.board_project.domain.boardCommentLike;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                boardCommentLike.getBoardId(), boardCommentLike.getCommentId(),
                boardCommentLike.getCommentSeq(), boardCommentLike.getMemberId());
    }

    //member가 특정 board에서 좋아요 누른 댓글들 가져오기
    public List<BoardCommentLikeDto> findLikedBoardComment(int boardId, String memberId) {
        String sql = """
        select comment_id, comment_seq
        from board_comment_like
        where board_id = ?
        and member_id = ? """;

        return jdbcTemplate.query(sql, new Object[]{boardId, memberId}, (rs, rowNum) ->
                BoardCommentLikeDto.builder()
                        .commentId(rs.getInt("comment_id"))
                        .commentSeq(rs.getInt("comment_seq"))
                        .build()
        );
    }

}
