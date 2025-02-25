package com.jun.board_project.global.util;

import com.jun.board_project.domain.member.member.Member;

public class FnparamToDimens {

    public static String [] fnparamToMemberDimens(int functionId, Member member) {

        switch (functionId) {
            case 1:
                return new String[] {member.getMemberRank(), member.getSeqloginYn(), member.getNewYn(), member.getMembershipYn()}; //고객등급, 연속출석여부, 신규여부, 멤버십여부
            case 2:
                return new String[] {member.getMemberRank(), member.getSeqloginYn(), "00000", "00000"}; //고객등급, 연속출석여부, DEFAULT, DEFAULT
            case 3:
                return new String[] {member.getMemberRank(), member.getNewYn(), member.getNewYn(), "00000"}; //고객등급, 연속출석여부, 신규여부, DEFAULT
            default:
                return new String[] {};
        }


    }
}
