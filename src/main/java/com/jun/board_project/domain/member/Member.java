package com.jun.board_project.domain.member;

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


}
