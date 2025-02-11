package com.jun.board_project.domain.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerService {
    private final SchedulerRepository schedulerRepository;

    public void saveTopMember() {
        schedulerRepository.saveTopMember();
    }
}
