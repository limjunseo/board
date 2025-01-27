package com.jun.board_project.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MemberForm {

    private String memberId;

    private String memberPw;

    private String memberConfirmPw;

    private String memberName;


    public Member toMember() {
        return Member.builder()
                .memberId(memberId)
                .memberPw(memberPw)
                .memberName(memberName)
                .build();
    }

}
