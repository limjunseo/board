package com.jun.board_project.domain.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final SchedulerService schedulerService;

    @Scheduled(cron = "0 0 0 * * *")
    public void saveTopMember() {
        schedulerService.saveTopMember(); //일일단위 우수멤버 선정 스케줄
    }
}
