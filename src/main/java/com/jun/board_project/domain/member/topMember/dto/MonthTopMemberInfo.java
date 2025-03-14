package com.jun.board_project.domain.member.topMember.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
@Setter
public class MonthTopMemberInfo {
    private String memberId;

    private String ranking;

    private int selectedCntInThisMonth;
}
