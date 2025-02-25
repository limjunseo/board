package com.jun.board_project.domain.admin.ruleMatrix;

import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
@Setter
public class RuleMatrixForm {
    int functionId;

    int cellId;

    String dimen1Value;

    String dimen2Value;

    String dimen3Value;

    String dimen4Value;
}
