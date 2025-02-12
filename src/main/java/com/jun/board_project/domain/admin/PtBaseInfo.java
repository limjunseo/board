package com.jun.board_project.domain.admin;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter @NoArgsConstructor @Setter
public class PtBaseInfo {
    private int ptBaseId;

    private int functionId;

    private String ptBaseName;
}
