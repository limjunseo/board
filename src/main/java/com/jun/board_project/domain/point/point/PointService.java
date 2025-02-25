package com.jun.board_project.domain.point.point;

import com.jun.board_project.domain.admin.ruleMatrix.RuleMatrixService;
import com.jun.board_project.domain.admin.ruleSet.RuleSetService;
import com.jun.board_project.domain.member.member.Member;
import com.jun.board_project.domain.member.member.MemberService;
import com.jun.board_project.domain.point.pointBase.PointBaseCd;
import com.jun.board_project.global.util.FnparamToDimens;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;
    private final MemberService memberService;
    private final RuleSetService ruleSetService;
    private final RuleMatrixService ruleMatrixService;

    public void savePoint(String memberId, PointBaseCd pointBaseCd) {

        //포인트 지급지출소멸 구분코드와 포인트기본목록 코드 설정
        Point point = new Point(memberId, PointCd.POINT_GIVE, pointBaseCd.getCode(), LocalDateTime.now());

        int seq = pointRepository.findSeqByMemberId(memberId);
        point.setSeq(seq); //포인트 일련번호 설정

        //멤버 정보 조회
        Member member = memberService.findMemberById(memberId);
        String [] dimensValue = FnparamToDimens.fnparamToMemberDimens(pointBaseCd.getFunctionId(), member);


        //멤버 정보로 포인트값 가져오기
        int pointValue = ruleSetService.findTargetValue(pointBaseCd, dimensValue);
        point.setValue(pointValue);
        pointRepository.savePoint(point);
    }

    //멤버가 받을 수 있는 포인트값 조회 ex)출석포인트, 게시글작성포인트
    public int findPoint(String memberId, PointBaseCd pointBaseCd) {
        Member member = memberService.findMemberById(memberId);
        String [] dimensValue = FnparamToDimens.fnparamToMemberDimens(pointBaseCd.getFunctionId(), member);
        
        return ruleSetService.findTargetValue(pointBaseCd, dimensValue);
    }

}
