package com.jun.board_project.domain.board;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public Board save(Board board) {
        System.out.println("board = " + board);
        String sql =
                "insert into board (board_id, member_id, board_ct_id, board_title, board_created_dt) values (?, ?, ?, ?, ?)";


        jdbcTemplate.update(sql,board.getBoardId(), board.getMemberId(), board.getBoardCtId(), board.getBoardTitle(),
                new java.sql.Timestamp(System.currentTimeMillis()));
        return board;
    }

    public Board update(Board board) {
//        jdbcTemplate.update("UPDATE post SET title = ?, content = ? WHERE postNm = ?",
        return null;
    }

    //max + 1 채번
    public int nextVal() {
        String sql = "select nvl(max(board_id), 1) + 1 from board";
        return jdbcTemplate.queryForObject(sql, int.class);
    }

    //특정 게시글 상세내용과 좋아요수 반환
    public BoardDto findById(int boardId) {

        String sql = """
    select a.board_id, a.board_title, a.board_created_dt, a.board_ct_id, b.board_content,
           (select count(*) from board_like where board_id = a.board_id) as like_cnt
    from board a, board_detail b
    where a.board_id = b.board_id and a.board_id = ?
    """;

        return jdbcTemplate.queryForObject(sql, new BoardDtoRowMapper() , boardId);
    }

    //게시글별 좋아요수 반환
    //특정 카테고리 게시글 조회 페이지별로 10개씩 반환
    public List<BoardCtPageDto> findAllByBoardCtId(String boardCtId, int page) {
        String sql = """
    SELECT a.*,
           (SELECT count(*)
            FROM BOARD_LIKE 
            WHERE board_id = a.board_id) AS like_count
    FROM (
        SELECT rownum rn, t.*
        FROM (
            SELECT *
            FROM BOARD 
            ORDER BY board_id DESC
        ) t
        WHERE ROWNUM <= (:page) * 10
    ) a
    WHERE rn >= (:page - 1) * 10 + 1
    """;


        return jdbcTemplate.query(sql, new BoardCtPageDtoRowMapper(), boardCtId);
    }

    //특정 회원이 북마크한 게시글 리스트 반환
    public List<BoardCtPageDto> findBookmarkedBoardList(String memberId) {
        String sql = """
            select a.*,
                 (select count(*) from board_like where board_id = a.board_id) as board_like_cnt
            from board a, board_bookmark b
            where b.member_id = ?
            and a.board_id = b.board_id
    """;


        return jdbcTemplate.query(sql, new BoardCtPageDtoRowMapper(), memberId);


    }


}
