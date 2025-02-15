package com.jun.board_project.domain.memberLoginHis;

import com.jun.board_project.domain.point.PointBaseCd;
import com.jun.board_project.domain.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LoginSuccessService {
    private final MemberLoginHisService memberLoginHisService;
    private final PointService pointService;

    public void handleLoginSuccess(String memberId) {
        MemberLoginHis memberLoginHis = new MemberLoginHis(memberId, LocalDateTime.now());
        memberLoginHisService.saveMemberLoginHis(memberLoginHis);
        pointService.savePoint(memberId, PointBaseCd.LOGIN);
    }
}
