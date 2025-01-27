package com.jun.board_project.domain.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class MemberRank {
    private Long memberId;

    //회원등급 시작일자 varchar(8)
    private char startDate;

    //회원등급 종료일자 varchar(8)
    private char endDate;

    //pk

    //랭크 코드
    private String rankCode;

    public static String defaultRank() {
        return "06";
    }
}
