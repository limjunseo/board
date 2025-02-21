package com.jun.board_project.global.util.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void insertUpdateCompleteLog() {
        int updateLogId = logRepository.nextVal();
        UpdateLogInfo updateLogInfo = new UpdateLogInfo(updateLogId, LocalDateTime.now());
        logRepository.insertUpdateCompleteLog(updateLogInfo);
    }

    public LocalDateTime findLastUpdateDt() {
        return logRepository.findLastUpdateDt();
    }

}
