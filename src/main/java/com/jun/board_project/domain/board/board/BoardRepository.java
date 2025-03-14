package com.jun.board_project.domain.board.board;


import com.jun.board_project.domain.board.board.dto.BoardInfo;
import com.jun.board_project.domain.board.board.dto.BoardInfoRowMapper;
import com.jun.board_project.domain.board.board.dto.BoardMainPageInfo;
import com.jun.board_project.domain.board.board.dto.BoardMainPageInfoRowMapper;
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
    public BoardInfo findById(int boardId) {

        String sql = """
    select a.board_id, a.board_title, a.board_created_dt, a.board_ct_id, b.board_content,
           (select count(*) from board_like where board_id = a.board_id) as like_cnt
    from board a, board_detail b
    where a.board_id = b.board_id 
    and a.board_id = ?
    """;

        return jdbcTemplate.queryForObject(sql, new BoardInfoRowMapper() , boardId);
    }

    //게시글별 좋아요수 반환
    //특정 카테고리 게시글 조회 페이지별로 10개씩 반환
    public List<BoardMainPageInfo> findAllByBoardCtId(String boardCtId, int page) {
        String sql = """
    SELECT a.*,
           (SELECT count(*)
            FROM BOARD_LIKE 
            WHERE board_id = a.board_id) AS board_like_cnt
    FROM (
        SELECT rownum rn, t.*
        FROM (
            SELECT *
            FROM BOARD 
            WHERE board_ct_id = ?
            ORDER BY board_id DESC
        ) t
        WHERE ROWNUM <= ? * 10
    ) a
    WHERE rn >= (? - 1) * 10 + 1
    """;


        return jdbcTemplate.query(sql, new BoardMainPageInfoRowMapper(), boardCtId, page, page);
    }

    //특정 회원이 북마크한 게시글 리스트 반환
    public List<BoardMainPageInfo> findBookmarkedBoardList(String memberId) {
        String sql = """
            select a.*,
                 (select count(*) from board_like where board_id = a.board_id) as board_like_cnt
            from board a, board_bookmark b
            where b.member_id = ?
            and a.board_id = b.board_id
    """;


        return jdbcTemplate.query(sql, new BoardMainPageInfoRowMapper(), memberId);

    }

    public List<BoardMainPageInfo> findHotBoard(String boardCtId, int page) {
        String sql = """
        SELECT b.*, a.board_like_cnt
        FROM (
            SELECT ROWNUM rn, t.*
            FROM (
                SELECT /*+ leading(b) index_ffs(b BOARD_X1) use_hash(a) no_place_group_by */ 
                       b.board_id, COUNT(*) board_like_cnt
                FROM board_like a, board b
                WHERE b.BOARD_CREATED_DT >= TRUNC(SYSDATE - 7)
                AND b.board_id = a.board_id
                GROUP BY b.board_id
                ORDER BY board_like_cnt DESC
            ) t
            WHERE ROWNUM <= (? * 10)
        ) a, board b
        WHERE rn >= (? - 1) * 10 + 1
        AND a.board_id = b.board_id
        ORDER BY board_like_cnt DESC
        """;


            return jdbcTemplate.query(sql, new BoardMainPageInfoRowMapper(), page, page);
    }



}
