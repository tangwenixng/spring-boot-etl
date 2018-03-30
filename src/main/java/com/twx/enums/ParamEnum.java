package com.twx.enums;

import lombok.Getter;

@Getter
public enum ParamEnum {
    WARN_TYPE_IS_NULL(11,"预警类型为空"),
    WARN_TYPE_ERROR(12,"预警类型错误")
    ;

    private Integer code;
    private String message;

    ParamEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
