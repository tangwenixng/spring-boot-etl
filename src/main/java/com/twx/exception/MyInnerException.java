package com.twx.exception;

import com.twx.enums.ParamEnum;

public class MyInnerException extends RuntimeException{

    private Integer code;

//    public MyInnerException(ParamEnum paramEnum) {
//        super(paramEnum.getMessage());
//        this.code = code;
//    }

    public MyInnerException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
