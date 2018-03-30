package com.twx.exception;

import com.twx.enums.ParamEnum;

public class DBException extends RuntimeException {

    private Integer code;

    public DBException(ParamEnum paramEnum) {
        super(paramEnum.getMessage());
        this.code = code;
    }

    public DBException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
