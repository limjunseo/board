package com.jun.board_project.domain.board;

import com.jun.board_project.domain.boardLike.BoardLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;


    //새 글 작성 페이지
    @RequestMapping(value = "/board/new", method = RequestMethod.GET)
    public String getBoardNewPage(@ModelAttribute BoardForm boardForm) {
        boardService.save(boardForm);
        return "board/newBoardForm";
    }

    //게시글과 게시글 상세 정보 조회
    @RequestMapping(value = "/board/{boardCtId}/{boardId}", method = RequestMethod.GET)
    public String getBoard(@PathVariable("boardId") Long boardId, ModelMap model) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board";
    }

    //특정 카테고리 게시글 리스트 조회
    @RequestMapping(value = "/board/{boardCtId}", method = RequestMethod.GET)
    public String getBoardList(@PathVariable("boardCtId") String boardCtId, ModelMap model) {
        List<Board> boardList = boardService.getBoardList(boardCtId);
        model.addAttribute("boardList", boardList);
        return "board/boardListPage";
    }

    //새글 작성
    @RequestMapping(value = "/board/{boardCtCdId}", method = RequestMethod.POST)
    public String save(@ModelAttribute BoardForm boardForm) {
        boardService.save(boardForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/board/{boardId}/user/{userId}", method = RequestMethod.POST)
    public String like(@PathVariable("boardId") int boardId, @PathVariable("userId") String userId) {
        BoardLike boardLike = BoardLike.builder()
                .boardId(boardId)
                .userId(userId)
                .build();
        boardService.like(boardLike);
        return "redirect:/";
    }


    


}