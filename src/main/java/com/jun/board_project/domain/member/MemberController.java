package com.jun.board_project.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    
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
}
