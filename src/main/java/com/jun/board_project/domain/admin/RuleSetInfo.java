package com.jun.board_project.domain.admin;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter @ToString
@Getter
public class RuleSetInfo {
    private int ptBaseId;

    private int functionId;

    private int cellId;

    private String startDt;

    private String endDt;

    private int targetValue;

    private String dimen1;

    private String dimen2;

    private String dimen3;

    private String dimen4;

    private String dimen1Name;

    private String dimen2Name;

    private String dimen3Name;

    private String dimen4Name;
}
