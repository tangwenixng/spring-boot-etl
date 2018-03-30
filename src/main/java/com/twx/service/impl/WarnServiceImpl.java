package com.twx.service.impl;

import com.google.gson.Gson;
import com.twx.VO.EditVO;
import com.twx.clean.DataCleaner;
import com.twx.db.MonitorMapper;
import com.twx.db.param.MonitorQueryParam;
import com.twx.dto.JsonParamDto;
import com.twx.entity.WarnEntity;
import com.twx.enums.WarnTypeEnum;
import com.twx.exception.DBException;
import com.twx.exception.FrontException;
import com.twx.exception.MyInnerException;
import com.twx.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class WarnServiceImpl implements WarnService {

    @Autowired
    private MonitorMapper monitorMapper;


    @Override
    public Map<String, Object> queryEchartsLine() {
        List<Map<String, String>> dbResult = monitorMapper.querySummary();
        return DataCleaner.toEchartsLine(dbResult);
    }

    @Override
    public List<WarnEntity> query(String warnTitle, String warnType, Integer isActivated,int page,int size) {
        warnTitle="%"+warnTitle+"%";

        List<String> types = new ArrayList<>();
        if (StringUtils.isEmpty(warnType)) {
            for (WarnTypeEnum typeEnum : WarnTypeEnum.values()) {
                types.add(typeEnum.getType());
            }
        }else {
            types.add(warnType);
        }

        List<Boolean> activates = new ArrayList<>();
        if (isActivated == -1) {
            activates.addAll(Arrays.asList(true, false));
        }else{
            Boolean b = isActivated==1?true:false;
            activates.add(b);
        }

        MonitorQueryParam param = new MonitorQueryParam();
        param.setWarningTitle(warnTitle);
        param.setWarningTypes(types);
        param.setActivateLists(activates);
        int offset = (page-1)*size;
        param.setOffset(offset);
        param.setSize(size);
        return monitorMapper.query(param);
    }

    @Override
    public int queryTotalSize(String warnTitle,String warnType,Integer isActivated) {
        warnTitle="%"+warnTitle+"%";

        List<String> types = new ArrayList<>();

        if (StringUtils.isEmpty(warnType)) {
            for (WarnTypeEnum typeEnum : WarnTypeEnum.values()) {
                types.add(typeEnum.getType());
            }
        }else {
            types.add(warnType);
        }

        List<Boolean> activates = new ArrayList<>();
        if (isActivated == -1) {
            activates.addAll(Arrays.asList(true, false));
        }else{
            Boolean b = isActivated==1?true:false;
            activates.add(b);
        }
        return monitorMapper.queryTotal(warnTitle, types, activates);
    }

    @Override
    public WarnEntity edit(Integer jobMasterId) {
        return monitorMapper.edit(jobMasterId);
    }




    @Override
    @Transactional
    public int save(EditVO editVO) {
        //1. 通过jobId找到原来的json字符串
        String jsonParam = monitorMapper.findJsonParameterByJobId(editVO.getJobMasterId());

        String jsonParameter=null;
        if (jsonParam != null) {
            //2. 设置其中的阈值
            //3. 判断前端传过来的对象中的阈值是否为空--前端有可能不传-不传就为空
            if (editVO.getThreshold() != null) {
                try{
                    Gson gson = new Gson();
                    JsonParamDto jsonParamDto = gson.fromJson(jsonParam, JsonParamDto.class);
                    jsonParamDto.setWarningThreshold(String.valueOf(editVO.getThreshold()));

                    jsonParameter = gson.toJson(jsonParamDto);
                }catch (Exception e){
                    throw new MyInnerException(401,"【Gson解析】jsonParameter出错了,jsonParam="+jsonParam);
                }
            }else{
                jsonParameter = jsonParam;
            }
        }

        int update1 = monitorMapper.updateJobMaster(jsonParameter, editVO.getActivated(), editVO.getVersion(), editVO.getJobMasterId());
        if (update1 == 0) {
            throw new DBException(21,"【更新JobMaster表】失败");
        }
        int update2 = monitorMapper.updateWarnTemplateByJobMasterId(editVO.getWarningType(), editVO.getWarningTitle(), editVO.getJobMasterId());
        if (update2 == 0) {
            throw new DBException(22,"【更新WarnTemplate表】失败");
        }
        return 0;
    }
}
