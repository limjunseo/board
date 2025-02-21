package com.jun.board_project.domain.memberLoginHis;

import com.jun.board_project.domain.point.pointBase.PointBaseCd;
import com.jun.board_project.domain.point.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LoginSuccessService {
    private final MemberLoginHisService memberLoginHisService;
    private final PointService pointService;

    public void handleLoginSuccess(String memberId) {
        String isFirstLogin = memberLoginHisService.isFirstLogin(memberId);
        MemberLoginHis memberLoginHis = new MemberLoginHis(memberId, LocalDateTime.now());
        memberLoginHisService.saveMemberLoginHis(memberLoginHis);

        //첫 로그인이 아니면 return;
        if (isFirstLogin.equals("N")) {
            return;
        }
        pointService.savePoint(memberId, PointBaseCd.LOGIN);
    }
}
