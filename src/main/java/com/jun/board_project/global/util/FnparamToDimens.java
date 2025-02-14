package com.jun.board_project.global.util;

import com.jun.board_project.domain.member.Member;

public class FnparamToDimens {

    public static String [] fnparamToMemberDimens(int functionId, Member member) {

        switch (functionId) {
            case 1:
                return new String[] {member.getMemberRank(), member.getSeqLoingYn(), member.getNewYn(), member.getMemberShipYn()}; //고갣으급, 연속출석여부, 신규여부, 멤버십여부
            case 2:
                return new String[] {member.getMemberRank(), member.getSeqLoingYn(), member.getNewYn(), "0"}; //고객등급, 연속출석여부, 신규여부, DEFAULT
            case 3:
                return new String[] {member.getMemberRank(), member.getNewYn(), "0", "0"}; //고객등급, 연속출석여부, DEFAULT, DEFAULT
            default:
                return new String[] {};
        }


    }
}
