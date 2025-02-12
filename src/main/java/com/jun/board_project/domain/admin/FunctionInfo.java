package com.jun.board_project.domain.admin;

import lombok.*;


@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class FunctionInfo {
    private int functionId;

    private String dimen1;

    private String dimen2;

    private String dimen3;

    private String dimen4;

    private String dimen1Name;

    private String dimen2Name;

    private String dimen3Name;

    private String dimen4Name;
}
