package com.jun.board_project.domain.boardBookmark;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardBookmarkRepository {
    private final JdbcTemplate jdbcTemplate;


    public void insertOrUpdate(BoardBookmark boardBookmark) {
        String sql = "merge into board_bookmark as target " +
                "using (select ? as board_id, ? as user_id) as source " +
                "on target.board_id = source.board_id " +
                "and target_user_id = source.user_id " +
                "when matched then " +
                "update set target.bookmark_yn = decode(target.bookmark_yn, 'Y', 'N', 'Y') " +
                "when not matched then " +
                "insert (board_id, user_id, bookmark_yn) values(source.board_id, source.user_id, 'Y')";

//        jdbcTemplate.update(sql, boardBookmark.getBoardId(), boardBookmark.getUserId());
    }

}
