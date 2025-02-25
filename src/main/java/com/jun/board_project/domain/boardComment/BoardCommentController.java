package com.jun.board_project.domain.boardComment;

import com.jun.board_project.domain.boardCommentLike.BoardCommentLike;
import com.jun.board_project.domain.boardCommentLike.BoardCommentLikeForm;
import com.jun.board_project.domain.member.member.MemberDetails;
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

    //기본댓글 POST 요청
    @RequestMapping(value = "/board/{boardCtId}/{boardId}/comment", method = RequestMethod.POST)
    public String saveComment(@PathVariable("boardCtId") String boardCtId,
                              @PathVariable("boardId") int boardId,
                              @ModelAttribute BoardCommentForm boardCommentForm,
                              @AuthenticationPrincipal MemberDetails member) {

        BoardComment boardComment = BoardComment.builder()
                .boardId(boardId)
                .memberId(member.getUsername())
                .commentContent(boardCommentForm.getCommentContent())
                .build();

        boardCommentService.saveComment(boardComment);
        return "redirect:/board/" + boardCtId + "/" + boardId;
    }


    //대댓글 POST 요청
    @RequestMapping(value = "/board/{boardCtId}/{boardId}/comment/{commentId}", method = RequestMethod.POST)
    public String saveCommentReply(@PathVariable("boardCtId") String boardCtId,
                              @PathVariable("boardId") int boardId,
                              @PathVariable("commentId") int commentId,
                              @ModelAttribute BoardCommentForm boardCommentForm,
                                 @AuthenticationPrincipal MemberDetails member) {


        BoardComment boardComment = BoardComment.builder()
                .boardId(boardId)
                .commentId(commentId)
                .memberId(member.getUsername())
                .commentContent(boardCommentForm.getCommentContent())
                .build();

        boardCommentService.saveCommentRe(boardComment);

        return "redirect:/board/" + boardCtId + "/" + boardId;

    }
    
    //댓글 좋아요 요청
    @RequestMapping(value = "/board/{boardCtId}/{boardId}/comment/like", method = RequestMethod.POST)
    public String saveCommentLike(@PathVariable("boardCtId") String boardCtId,
                                  @PathVariable("boardId") int boardId,
                                  @ModelAttribute BoardCommentLikeForm boardCommentLikeForm,
                                  @AuthenticationPrincipal MemberDetails member) {

        BoardCommentLike boardCommentLike
                = BoardCommentLike.builder()
                .commentId(boardCommentLikeForm.getCommentId())
                .boardId(boardId)
                .commentSeq(boardCommentLikeForm.getCommentSeq())
                .memberId(member.getUsername()).build();

        System.out.println(boardCommentLike);

        boardCommentService.saveBoardCommentLike(boardCommentLike);
        return "redirect:/board/" + boardCtId + "/" + boardId;
    }


}
