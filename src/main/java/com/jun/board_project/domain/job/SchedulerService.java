package com.jun.board_project.domain.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerService {
    private final TopMemberHisRepository topMemberHisRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void saveTopMember() {
        topMemberHisRepository.saveTopMember();
    }
}
