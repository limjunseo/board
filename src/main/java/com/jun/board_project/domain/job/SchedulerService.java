package com.jun.board_project.domain.job;

import com.jun.board_project.domain.member.MemberService;
import com.jun.board_project.domain.member.TopMemberHisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerService {
    private final TopMemberHisRepository topMemberHisRepository;
    private final MemberService memberService;

    @Scheduled(cron = "0 0 0 * * *")
    public void saveTopMember() {
        topMemberHisRepository.calTopMember(); //회원 이력으로 우수 회원 구하기
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateMemberInfo(){
        memberService.updateMemberInfo();
    }



}
