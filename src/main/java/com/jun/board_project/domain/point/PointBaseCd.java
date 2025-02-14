package com.jun.board_project.domain.point;

import lombok.Getter;

//포인트기본코드
public enum PointBaseCd {
    LOGIN(1,1),
    BOARD_WRITE(2,2),
    COMMENT_WRITE(3,3),
    BOARD_LIKE(4,4);

    @Getter
    private final int code;
    @Getter
    private final int functionId;

    PointBaseCd(int code, int functionId) {
        this.code = code;
        this.functionId = functionId;
    }
}
