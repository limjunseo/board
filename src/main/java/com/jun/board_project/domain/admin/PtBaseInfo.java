package com.jun.board_project.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PtBaseInfo {
    private int ptBaseId;

    private int functionId;

    private String ptBaseName;
}
