package com.dapan.sell.enums;

public enum ActivityStatusEnum implements CodeEnumEx {

    ACTIVITYING(1, "推广中"),
    ;
    private Integer code;
    private String message;

    ActivityStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
