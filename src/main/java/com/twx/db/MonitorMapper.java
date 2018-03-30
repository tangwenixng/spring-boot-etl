package com.twx.db;

import com.twx.VO.EditVO;
import com.twx.db.param.MonitorQueryParam;
import com.twx.entity.WarnEntity;
import com.twx.sqlprovider.MonitorProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MonitorMapper {

    /**
     * 查询详情
     * @param param
     * @return
     */
      List<WarnEntity> query(MonitorQueryParam param);

    /**
     * 获取分页总数
     * @param warningTitle
     * @param warningTypes
     * @param activateLists
     * @return
     */
    @SelectProvider(type = MonitorProvider.class,method = "queryTotal")
    int queryTotal(String warningTitle,List<String> warningTypes, List<Boolean> activateLists);

    /**
     * 取出首页需要的最近7天的echarts line数据
     * @return
     */
    List<Map<String,String>> querySummary();


    @Select("SELECT j.JobMasterID,w.WarningTitle,w.WarningType,j.IsActivated,j.JsonParameter,j.Version " +
            " FROM WarningTemplate w INNER join JobMaster j ON w.WarningCode = j.WarningCode " +
            " WHERE j.JobMasterID=#{jobMasterId}")
    @Results({
            @Result(property = "jobMasterId",column = "JobMasterID",javaType = Integer.class),
            @Result(property = "warningTitle",column = "WarningTitle",javaType = String.class),
            @Result(property = "warningType",column = "WarningType",javaType = String.class),
            @Result(property = "isActivated",column = "IsActivated",javaType = Boolean.class),
            @Result(property = "jsonParameter",column = "JsonParameter",javaType = String.class),
            @Result(property = "version",column = "Version",javaType = Integer.class)
    })
    WarnEntity edit(Integer jobMasterId);

    @Select("SELECT j.JsonParameter FROM JobMaster j WHERE j.JobMasterID=#{jobMasterId}")
    String findJsonParameterByJobId(Integer jobMasterId);


    @Update("UPDATE  j SET j.jsonParameter=#{jsonParameter}, j.Version=#{version},j.IsActivated=#{isActivated} FROM JobMaster j WHERE j.JobMasterID=#{jobMasterId}")
    int updateJobMaster(@Param("jsonParameter") String jsonParameter,
                        @Param("isActivated") boolean isActivated,
                        @Param("version") Integer version,
                        @Param("jobMasterId") Integer jobMasterID);

    @Update("UPDATE w SET w.WarningType=#{warningType},w.WarningTitle=#{warningTitle} FROM WarningTemplate w WHERE w.WarningCode=(SELECT j.WarningCode FROM JobMaster j WHERE j.JobMasterID=#{jobMasterId})")
    int updateWarnTemplateByJobMasterId(@Param("warningType") String warningType,
                                        @Param("warningTitle") String warningTitle,
                                        @Param("jobMasterId") Integer jobMasterId);
}
