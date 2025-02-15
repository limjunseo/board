package com.jun.board_project.domain.point;

import com.jun.board_project.domain.admin.RuleMatrixService;
import com.jun.board_project.domain.admin.RuleSetService;
import com.jun.board_project.domain.member.Member;
import com.jun.board_project.domain.member.MemberService;
import com.jun.board_project.global.util.FnparamToDimens;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;
    private final MemberService memberService;
    private final RuleSetService ruleSetService;
    private final RuleMatrixService ruleMatrixService;

    public void savePoint(String memberId, PointBaseCd pointBaseCd) {
        Point point = new Point(memberId, PointCd.POINT_GIVE.getCode()); //포인트 지급지출소멸 구분코드 설정/ 여기서는 지급코드 설정

        int seq = pointRepository.findSeqByMemberId(memberId);
        point.setSeq(seq); //포인트 일련번호 설정

        //멤버 정보 조회
        Member member = memberService.findMemberById(memberId);
        String [] dimensValue = FnparamToDimens.fnparamToMemberDimens(pointBaseCd.getFunctionId(), member);


        //멤버 정보로 포인트값 가져오기
        int pointValue = ruleSetService.findTargetValue(pointBaseCd, dimensValue);
        pointRepository.savePoint(point);
    }

}
