package com.twx.db;

import com.google.gson.Gson;
import com.twx.clean.DataCleaner;
import com.twx.db.param.MonitorQueryParam;
import com.twx.entity.WarnEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorMapperTest {
    @Autowired
    private MonitorMapper monitorMapper;

//    @Test
//    public void query() throws Exception {
//        List<WarnEntity> tasks = monitorMapper.query("%集装箱%", "WaitDoingTask",true);
//        Assert.assertNotEquals(0,tasks.size());
//    }

    @Test
    public void query() throws Exception {
//        List<WarnEntity> tasks = monitorMapper.query("%集装箱%", Arrays.asList("WaitDoingTask"),Arrays.asList(true,false),1,5);
        MonitorQueryParam param = new MonitorQueryParam();
        param.setWarningTitle("%集装箱%");
        param.setWarningTypes(Arrays.asList("WaitDoingTask"));
        param.setActivateLists(Arrays.asList(true,false));
        param.setOffset(0);
        param.setSize(5);
        List<WarnEntity> tasks = monitorMapper.query(param);
        Assert.assertNotEquals(0,tasks.size());
    }

    @Test
    public  void queryTotal(){
        int total = monitorMapper.queryTotal("%%", Arrays.asList("WaitDoingTask","WaitReadingTask"), Arrays.asList(true, false));
        Assert.assertEquals(20,total);
    }

    @Test
    public void querySummary(){
        List<Map<String, String>> mapList = monitorMapper.querySummary();

        Map<String, Object> resMap = DataCleaner.toEchartsLine(mapList);

        Gson gson = new Gson();
        System.out.println(gson.toJson(resMap));
    }

    @Test
    public  void edit() {
        WarnEntity edit = monitorMapper.edit(10);
        Assert.assertNotNull(edit);
    }

    @Test
    public void updateJobMaster(){
        int res = monitorMapper.updateJobMaster("{\"AlertType\":\"2\",\"CustomsCode\":\"4218\",\"ExamAddrCode\":\"1405\",\"WarningThreshold\":\"100\"}", false, 4, 10);
        Assert.assertNotEquals(0,res);
    }

    @Test
    public void updateWarnTemplateByJobMasterId() {
        int res = monitorMapper.updateWarnTemplateByJobMasterId("WaitDoingTask", "南港区待处理集装箱量临近待处理总箱位数自动监测预警", 10);
        Assert.assertNotEquals(0,res);
    }

    @Test
    public void findJsonParameterByJobId(){
        String result = monitorMapper.findJsonParameterByJobId(10);
        System.out.println(result);
        Assert.assertNotNull(result);
    }


}