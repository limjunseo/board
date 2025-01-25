package com.jun.board_project.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value = "/board/{boardId}", method = RequestMethod.GET)
    public String getBoard(@PathVariable("boardId") Long boardId, ModelMap model) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board";
    }

    //새글 작성
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public String save(@ModelAttribute BoardForm boardForm) {
        boardService.save(boardForm);
        return "redirect:/";
    }



    


}