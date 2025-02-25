package com.jun.board_project.domain.member.member;

import lombok.*;

@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class Member {

    private String memberId;

    private String memberPw;

    private String memberName;

    private String memberRank;

    private String memberCreatedDt;

    private String seqloginYn; //연속출석여부

    private String newYn; //멤버 신규여부, memberCreatedDt가 7일이내면 'Y'

    private String membershipYn; //멤버십여부

}
