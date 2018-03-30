package com.twx.exception;

import com.twx.enums.ParamEnum;
import lombok.Getter;
import org.apache.ibatis.annotations.Param;

@Getter
public class FrontException extends RuntimeException{

    private Integer code;

    public FrontException(ParamEnum paramEnum) {
        super(paramEnum.getMessage());
        this.code = code;
    }

    public FrontException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
