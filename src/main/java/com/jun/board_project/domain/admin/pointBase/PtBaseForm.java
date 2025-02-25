package com.jun.board_project.domain.admin.pointBase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PtBaseForm { //포인트 기본목록
    private int ptBaseId;

    private int functionId;

    private String ptBaseName;

    private String ptBaseDescription;
}
