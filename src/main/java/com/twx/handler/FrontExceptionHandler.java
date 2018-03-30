package com.twx.handler;

import com.twx.VO.ResultVO;
import com.twx.exception.FrontException;
import com.twx.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class FrontExceptionHandler {

    @ExceptionHandler(FrontException.class)
    @ResponseBody
    public ResultVO handlerFrontException(FrontException e) {
        log.error("【前端参数出错了】....");
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }


}
