package com.twx.transfer;

import com.twx.VO.QueryVO;
import com.twx.entity.WarnEntity;
import com.twx.enums.WarnTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WarnEntity2QueryVOTransferTest {

    @Test
    public void transfer() throws Exception {
        WarnEntity warnEntity = new WarnEntity();
        warnEntity.setJobMasterId(1);
        warnEntity.setWarningTitle("测试");
        warnEntity.setWarningType(WarnTypeEnum.DOING.getType());
        warnEntity.setIsActivated(true);

        QueryVO queryVO = WarnEntity2QueryVOTransfer.transfer(warnEntity);
        Assert.assertNotNull(queryVO);
    }

    @Test
    public void transfer1() throws Exception {
        WarnEntity warnEntity = new WarnEntity();
        warnEntity.setJobMasterId(1);
        warnEntity.setWarningTitle("测试");
        warnEntity.setWarningType(WarnTypeEnum.DOING.getType());
        warnEntity.setIsActivated(true);

        List<QueryVO> queryVOList = WarnEntity2QueryVOTransfer.transfer(Arrays.asList(warnEntity));
        Assert.assertNotEquals(0,queryVOList.size());
    }

}