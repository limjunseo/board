package com.jun.board_project.domain.BoardComment;

import com.jun.board_project.domain.member.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @RequestMapping(value = "/board/{boardCtId}/{boardId}/comment", method = RequestMethod.POST)
    public String saveComment(@PathVariable("boardCtId") String boardCtId,
                              @PathVariable("boardId") int boardId,
                              @ModelAttribute BoardCommentForm boardCommentForm,
                              @AuthenticationPrincipal MemberDetails member) {
        boardCommentForm.setMemberId(member.getUsername());
        BoardComment boardComment = boardCommentForm.toBoard();
        boardCommentService.saveComment(boardComment);
        return "redirect:/board/" + boardCtId + "/" + boardId;
    }


    //대댓글 POST 요청
    @RequestMapping(value = "/board/{boardCtId}/{boardId}/comment/{commentId}", method = RequestMethod.POST)
    public void saveCommentReply(@PathVariable("boardCtId") String boardCtId,
                              @PathVariable("boardId") int boardId,
                              @PathVariable("commentId") int commentId,
                              @ModelAttribute BoardCommentForm boardCommentReplyForm) {
        BoardComment boardComment = boardCommentReplyForm.toBoard();
        boardCommentService.saveCommentRe(boardComment);


    }


}
