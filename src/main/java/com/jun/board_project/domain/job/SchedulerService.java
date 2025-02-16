package com.jun.board_project.domain.job;

import com.jun.board_project.domain.member.MemberService;
import com.jun.board_project.domain.member.TopMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerService {
    private final TopMemberRepository topMemberRepository;
    private final MemberService memberService;

    public void calTopMember() {
        topMemberRepository.calTopMember(); //회원 이력으로 우수 회원 구하기
    }

    public void updateMemberInfo(){
        memberService.updateSeqloginYn();
//        memberService.updateMemberRank();
//        memberService.updateMembershipYn();
    }



}
