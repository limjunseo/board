package com.jun.board_project.domain.admin.ruleSet;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class RuleSetForm {
    private int ptBaseId;

    private int functionId;

    private int cellId;

    private String startDt;

    private String endDt;

    private int targetValue;
}
