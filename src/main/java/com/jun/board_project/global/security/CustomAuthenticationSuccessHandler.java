package com.jun.board_project.global.security;

import com.jun.board_project.domain.memberLoginHis.MemberLoginHis;
import com.jun.board_project.domain.memberLoginHis.MemberLoginHisService;
import com.jun.board_project.domain.point.PointBaseCd;
import com.jun.board_project.domain.point.PointService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberLoginHisService memberLoginHisService;
    private final PointService pointService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String memberId = authentication.getName();

        //로그인 이력 저장
        MemberLoginHis memberLoginHis = new MemberLoginHis(memberId, LocalDateTime.now());
        memberLoginHisService.saveMemberLoginHis(memberLoginHis);

        //로그인시 금일 로그인 최초 여부확인 후 출석포인트 지급
        if(memberLoginHisService.isFirstLogin(memberId).equals("Y")){
            pointService.savePoint(memberId, PointBaseCd.LOGIN);
        }

        // 로그인 성공 후 기본 페이지로 리다이렉트
        response.sendRedirect("/");
    }


}
