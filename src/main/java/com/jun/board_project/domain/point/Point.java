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

    public void setSeq(int seq) {
        this.Seq = seq;
    }


    public Point(String memberId, String pointCd) {
        this.memberId = memberId;
        this.pointCd = pointCd;
    }
}
