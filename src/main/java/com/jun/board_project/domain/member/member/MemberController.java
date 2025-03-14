package com.jun.board_project.domain.member.member;

import com.jun.board_project.domain.board.boardCt.BoardCtPageDto;
import com.jun.board_project.domain.board.board.BoardService;
import com.jun.board_project.domain.member.member.dto.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final BoardService boardService;
    
    //로그인페이지 이동
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "member/loginForm";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp() {
        return "member/signUpForm";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute MemberForm memberForm) {
        memberService.save(memberForm);
        return "redirect:/login";
    }

    //member 프로필 이동
    @RequestMapping(value = "/member/profile", method = RequestMethod.GET)
    public String getProfile(@AuthenticationPrincipal MemberDetails member, ModelMap model) {
        //로그인한 유저의 북마크 게시글들을 가져온다
        List<BoardCtPageDto> bookmarkedBoardList = boardService.getBookmarkedBoardList(member.getUsername());

        model.put("bookmarkedBoardList", bookmarkedBoardList);
        return "member/profile";
    }
}
