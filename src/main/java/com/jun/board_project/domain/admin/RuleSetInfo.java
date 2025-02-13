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

    private String dimen1Value;

    private String dimen2Value;

    private String dimen3Value;

    private String dimen4Value;

}
