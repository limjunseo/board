package com.jun.board_project.domain.point.point;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class PointModificationInfo {
    private String memberId;

    private int seq;

    private int value;

    private int ptBaseId;

    private LocalTime pointDt;
}
