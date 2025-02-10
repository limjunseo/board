package com.jun.board_project.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CodeMetaForm {
    private String codeMetaId;

    private String codeMetaName;
}
