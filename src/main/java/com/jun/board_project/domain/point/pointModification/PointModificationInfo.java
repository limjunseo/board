package com.jun.board_project.domain.point.pointModification;

import com.jun.board_project.domain.point.point.PointCd;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
@Getter
public class PointModificationInfo {
    private String memberId;

    private int seq;

    private int value;

    private int ptBaseId;

    private LocalTime pointDt;

    private PointCd pointCd;
}
