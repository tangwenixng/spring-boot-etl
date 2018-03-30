package com.twx.controller;

import com.twx.VO.EditVO;
import com.twx.VO.QueryVO;
import com.twx.VO.ResultVO;
import com.twx.entity.WarnEntity;
import com.twx.enums.ParamEnum;
import com.twx.enums.WarnTypeEnum;
import com.twx.exception.FrontException;
import com.twx.service.WarnService;
import com.twx.transfer.WarnEntity2EditVOTransfer;
import com.twx.transfer.WarnEntity2QueryVOTransfer;
import com.twx.utils.ResultVOUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by twx on 2017/11/18.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class APIController {

    @Autowired
    private WarnService warnService;

    /**
     * 获取最近七天的数据
     */
    @GetMapping("/dashbord")
    public ResultVO<Map<String, Object>> dashbord(){
        log.info("【进入首页】....");
        Map<String, Object> map = warnService.queryEchartsLine();
        return ResultVOUtil.success(map);
    }

    @PostMapping("/query")
    public ResultVO<Map<String, Object>> query(@RequestParam(value = "warningTitle",defaultValue = "") String warnTitle,
                                          @RequestParam(value = "warningType",defaultValue = "") String warnType,
                                          @RequestParam(value = "activated",defaultValue = "-1") Integer isActivated,
                                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size)
    {
        log.info("【进入查询方法】page={},size={}",page,size);

        if(!StringUtils.isEmpty(warnType)&&!WarnTypeEnum.isWarnType(warnType)){
            log.error("【查询方法-预警类型错误】 warnType={}",warnType);
            throw  new FrontException(ParamEnum.WARN_TYPE_ERROR.getCode(),ParamEnum.WARN_TYPE_ERROR.getMessage());
        }

        int totalSize = warnService.queryTotalSize(warnTitle, warnType, isActivated);
        List<WarnEntity> warnEntities = warnService.query(warnTitle, warnType, isActivated,page,size);
        log.info("【查询方法-查询结果集】共 "+warnEntities.size()+" 条");

        List<QueryVO> queryVOList = WarnEntity2QueryVOTransfer.transfer(warnEntities);

        Map<String, Object> map = new HashMap<>();
        map.put("total", totalSize);
        map.put("elements",queryVOList);

        return ResultVOUtil.success(map);
    }


    @GetMapping("/edit")
    public ResultVO<EditVO> edit(@RequestParam("jobMasterId") Integer jobMasterId) {
        log.info("【进入编辑方法】 jobId={}",jobMasterId);
        WarnEntity warnEntity = warnService.edit(jobMasterId);
        EditVO editVO = WarnEntity2EditVOTransfer.transfer(warnEntity);
        return ResultVOUtil.success(editVO);
    }

    @PostMapping(value = "/save",consumes = "application/json")
    public ResultVO save(@RequestBody  EditVO editVO) {
        log.info("【进入保存方法】...");
        int code = warnService.save(editVO);
        if (code==0)
            log.info("【保存JobMasterId={}成功】",editVO.getJobMasterId());
        return ResultVOUtil.success();

    }


}
