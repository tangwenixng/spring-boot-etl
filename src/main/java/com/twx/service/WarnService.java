package com.twx.service;

import com.twx.VO.EditVO;
import com.twx.entity.WarnEntity;

import java.util.List;
import java.util.Map;

public interface WarnService {

    /**
     * 获取首页echarts line需要的数据格式
     */
    Map<String, Object> queryEchartsLine();

    /**
     *
     * @param warnTitle 可为空值
     * @param warnType 空：所有类型
     * @param isActivated -1: 启用/禁用 1：启用 0：禁用
     * @param  page 页码
     * @param  size 当前页显示行数
     * @return
     */
    List<WarnEntity> query(String warnTitle,String warnType,Integer isActivated,int page,int size);

    /**
     * 查询 符合当前条件下的 总行数
     * @param warnTitle
     * @param warnType
     * @param isActivated
     * @return
     */
    int queryTotalSize(String warnTitle,String warnType,Integer isActivated);

    WarnEntity edit(Integer jobMasterId);

    int save(EditVO editVO);
}
