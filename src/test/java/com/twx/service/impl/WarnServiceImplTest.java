package com.twx.service.impl;

import com.twx.VO.EditVO;
import com.twx.entity.WarnEntity;
import com.twx.service.WarnService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarnServiceImplTest {
    @Autowired
    private WarnService warnService;

    @Test
    public void queryEchartsLine() {
        Map<String, Object> map = warnService.queryEchartsLine();
        Assert.assertNotEquals(null,map);
    }

    @Test
    public void query() throws Exception {
        List<WarnEntity> entities = warnService.query("集装箱", "WaitDoingTask", -1,1,5);
        Assert.assertNotEquals(0,entities.size());
    }


    @Test
    public void save() {
        EditVO editVO = new EditVO();
        editVO.setJobMasterId(10);
        editVO.setWarningTitle("南港区待处理集装箱量临近待处理总箱位数自动监测预警");
        editVO.setWarningType("WaitReadingTask");
        editVO.setActivated(true);
        editVO.setThreshold(100);
        editVO.setVersion(6);
        int res = warnService.save(editVO);
        Assert.assertNotEquals(-1,res);
    }
}