package com.jun.board_project.domain.boardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<BoardCommentDto> findCommentByBoardId(int boardId) {
        String sql = """
        select a.*,
                (select count(*)
                from board_comment_like
                where a.board_id = board_id
                and a.comment_id = comment_id
                and a.comment_seq = comment_seq) as like_cnt
        from board_comment a
        where a.board_id = ?""";



        return jdbcTemplate.query(sql, new BoardCommentDtoRowMapper(), boardId);
    }

    //기본 댓글, 대댓글 저장
    public int saveComment(BoardComment boardComment) {
        String sql =
                "insert into board_comment(board_id, comment_id, comment_seq, " +
                        "comment_content, member_id, comment_created_dt) "
                +"values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                boardComment.getBoardId(), boardComment.getCommentId(), boardComment.getCommentSeq(),
                boardComment.getCommentContent(), boardComment.getMemberId(),
                new java.sql.Timestamp(System.currentTimeMillis()));

        return boardComment.getCommentId();
    }




    //댓글 commentId 가져오기
    public int findCommentIdByBoardId(int boardId) {
        String sql =
                "select nvl(max(comment_id), 0) + 1 " //댓글이 하나도 없으면 comemntId 1 반환
                +"from board_comment "
                +"where board_id = ? ";
        return jdbcTemplate.queryForObject(sql, int.class, boardId);
    }

    //대댓글 commentSeq 가져오기
    public int findCommentSeqByBoardIdAndCommentId(int boardId, int commentId) {
        String sql =
                "select max(comment_seq) + 1 "
                +"from board_comment "
                +"where board_id = ? "
                +"and comment_id = ?";

        return jdbcTemplate.queryForObject(sql, int.class, boardId, commentId);
    }


}
