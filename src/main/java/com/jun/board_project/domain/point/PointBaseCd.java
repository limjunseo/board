package com.jun.board_project.domain.point;

//포인트기본코드
public enum PointBaseCd {
    LOGIN(0),
    BOARD_WRITE(1),
    COMMENT_WRITE(2),
    BOARD_LIKE(3);

    private final int code;

    PointBaseCd(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
