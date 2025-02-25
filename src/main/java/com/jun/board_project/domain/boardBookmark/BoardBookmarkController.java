package com.jun.board_project.domain.boardBookmark;

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
public class BoardBookmarkController {
    private final BoardBookmarkService boardBookmarkService;

    //북마크 저장 또는 삭제, 삭제시 bookmarkYn = 'N'으로 업데이트
    @RequestMapping(value = "/board/{boardCtId}/{boardId}/bookmark", method = RequestMethod.POST)
    public String saveOrDeleteBoardBookmark(@PathVariable("boardId") int boardId,
                                            @PathVariable("boardCtId") String boardCtId,
                                            @ModelAttribute BoardBookmark boardBookmark,
                                            @AuthenticationPrincipal MemberDetails member) {

        boardBookmark.setMemberId(member.getUsername());
        System.out.println("boardBookmark : " + boardBookmark);
        boardBookmarkService.saveOrBoardBookmark(boardBookmark);
        return "redirect:/board/" + boardCtId + "/" + boardId;
    }
}
