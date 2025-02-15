package com.jun.board_project.global.security;

import com.jun.board_project.domain.memberLoginHis.LoginSuccessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final LoginSuccessService loginSuccessService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String memberId = authentication.getName();

        System.out.println("로그인 성공 핸들러 실행");

        loginSuccessService.handleLoginSuccess(memberId);
        // 로그인 성공 후 기본 페이지로 리다이렉트
        response.sendRedirect("/");
    }


}
