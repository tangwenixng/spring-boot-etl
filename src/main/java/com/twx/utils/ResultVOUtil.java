package com.twx.utils;

import com.twx.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer errorCode,String errorMsg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(errorCode);
        resultVO.setMsg(errorMsg);
        return resultVO;
    }
}
