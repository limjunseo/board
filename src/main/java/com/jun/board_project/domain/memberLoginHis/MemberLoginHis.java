package com.jun.board_project.domain.memberLoginHis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @AllArgsConstructor
public class MemberLoginHis {
    private String memberId;

    private LocalDateTime loginDate;

    public void setTimestamp(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }
}
