package com.jun.board_project.domain.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @RequestMapping(value = "/board/{boardCtId}/{boardId}/comment/"
            ,method = RequestMethod.POST)
    public void saveComment(@PathVariable("boardCtId") String boardCtId,
                            @PathVariable("boardId") int boardId,
                            @ModelAttribute BoardCommentForm boardCommentForm) {
        BoardComment boardComment = boardCommentForm.toBoard();
        boardCommentService.save(boardComment);


    }


}
