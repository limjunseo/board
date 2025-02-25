package com.jun.board_project.domain.member.topMember;

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
