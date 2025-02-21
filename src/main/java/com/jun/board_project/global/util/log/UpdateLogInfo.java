package com.jun.board_project.global.util.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateLogInfo {
    private int updateLogId;

    private LocalDateTime updateDt;


}
