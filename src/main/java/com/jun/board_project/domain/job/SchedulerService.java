package com.jun.board_project.domain.job;

import com.jun.board_project.domain.member.member.MemberService;
import com.jun.board_project.domain.member.topMember.TopMemberRepository;
import com.jun.board_project.domain.point.pointBase.PointBaseService;
import com.jun.board_project.global.util.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerService {
    private final TopMemberRepository topMemberRepository;
    private final MemberService memberService;
    private final LogService logService;
    private final PointBaseService pointBaseService;

    public void calTopMember() {
        topMemberRepository.calTopMember(); //회원 이력으로 우수 회원 구하기
    }

//    @Transactional
//    public void updateMemberInfoAndPointInfo(){
//        memberService.updateSeqloginYn();
//        memberService.updateMemberRank();
//        memberService.updateMembershipYn();
//        pointBaseService.updatePointBase();
//        logService.insertUpdateMemberInfoAndPointBaseLog();
//    }

    public void insertUpdateCompleteLog() {
        logService.insertUpdateCompleteLog();
    }



}
