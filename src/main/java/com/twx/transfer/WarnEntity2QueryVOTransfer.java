package com.twx.transfer;

import com.twx.VO.QueryVO;
import com.twx.entity.WarnEntity;
import com.twx.enums.WarnTypeEnum;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WarnEntity2QueryVOTransfer {

    public static QueryVO transfer(WarnEntity warnEntity){
        QueryVO queryVO = new QueryVO();
        queryVO.setJobMasterId(warnEntity.getJobMasterId());
        queryVO.setWarningTitle(warnEntity.getWarningTitle());

        String warningType = warnEntity.getWarningType();
        if (warningType.equals(WarnTypeEnum.DOING.getType())) {
            queryVO.setWarningType(WarnTypeEnum.DOING.getDescription());
        }
        if (warningType.equals(WarnTypeEnum.READING.getType())) {
            queryVO.setWarningType(WarnTypeEnum.READING.getDescription());
        }

        Boolean isActivated = warnEntity.getIsActivated();
        if (isActivated) {
            queryVO.setActivated("启用");
        }else{
            queryVO.setActivated("禁用");
        }
        return queryVO;
    }

    public static List<QueryVO> transfer(List<WarnEntity> warnEntityList){
        return warnEntityList.stream().map(e -> transfer(e))
                .collect(Collectors.toList());
    }
}
