package com.jun.board_project.domain.point;

//포인트 지급사용소멸 구분코드
public enum PointCd {
    POINT_GIVE("01"),
    POINT_USE("02"),
    POINT_EXPIRE("03");

    private final String code;

    PointCd(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
