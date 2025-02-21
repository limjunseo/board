package com.jun.board_project.global.util.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void insertUpdateMemberInfoAndPointBaseLog() {
        int updateLogId = logRepository.nextVal();
        UpdateLogInfo updateLogInfo = new UpdateLogInfo(updateLogId, LocalDateTime.now());
        logRepository.insertUpdateMemberInfoAndPointBaseLog(updateLogInfo);
    }
}
