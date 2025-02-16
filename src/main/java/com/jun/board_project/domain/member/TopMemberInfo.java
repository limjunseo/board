package com.jun.board_project.domain.member;

import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
@Setter
public class TopMemberInfo {
    private String memberId;

    private String createdDt; //date

    private String memberRank;

    private int score;
}
