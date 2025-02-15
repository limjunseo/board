package com.jun.board_project.domain.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter @NoArgsConstructor @AllArgsConstructor
public class Point {
    private String memberId;

    //포인트 지급 일련번호
    private int Seq;

    //포인트 구분코드 - 포인트지급(003), 포인트사용(004), 포인트소멸(005)
    private String pointCd;

    private int value;

    //포인트기본목록 -> 포인트 지급 근거로 사용
    private int ptBaseId;

    public void setSeq(int seq) {
        this.Seq = seq;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Point(String memberId, String pointCd, int ptBaseId) {
        this.memberId = memberId;
        this.pointCd = pointCd;
        this.ptBaseId = ptBaseId;
    }
}
