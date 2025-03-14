package com.jun.board_project.domain.member.topMember.dto;

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

    private String ranking;

    private int score;
    
}
