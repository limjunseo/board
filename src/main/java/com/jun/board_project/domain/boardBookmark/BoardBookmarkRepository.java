package com.jun.board_project.domain.boardBookmark;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardBookmarkRepository {
    private final JdbcTemplate jdbcTemplate;


    public void insertOrUpdateBoardBookmark(BoardBookmark boardBookmark) {
        String sql = "merge into board_bookmark target " +
                "using (select ? board_id, ? member_id from dual) source " +
                "on (target.board_id = source.board_id " +
                "and target.member_id = source.member_id) " +
                "when matched then " +
                "update set target.bookmark_yn = decode(target.bookmark_yn, 'Y', 'N', 'Y') " +
                "when not matched then " +
                "insert (board_id, member_id, bookmark_yn) values(source.board_id, source.member_id, 'Y')";

        jdbcTemplate.update(sql, boardBookmark.getBoardId(), boardBookmark.getMemberId());
    }

    public String findBookmarkYn(int boardId, String memberId ) {
        String sql = """
            select nvl(
             (select bookmark_yn
                 from board_bookmark
                 where board_id = ?
                 and member_id = ?),'N')
            from dual
    """;
        //칼럼이 존재하지않거나 값이 없으면 N을 반환

        return jdbcTemplate.queryForObject(sql, String.class, boardId, memberId);
    }

}
