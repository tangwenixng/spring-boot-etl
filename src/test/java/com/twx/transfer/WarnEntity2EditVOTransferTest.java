package com.twx.transfer;

import com.twx.VO.EditVO;
import com.twx.entity.WarnEntity;
import com.twx.enums.WarnTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarnEntity2EditVOTransferTest {
    @Test
    public void transfer() throws Exception {
        WarnEntity warnEntity = new WarnEntity();
        warnEntity.setJobMasterId(1);
        warnEntity.setWarningTitle("测试");
        warnEntity.setWarningType(WarnTypeEnum.DOING.getType());
        warnEntity.setIsActivated(true);
        warnEntity.setJsonParameter("{\n" +
                "  \"AlertType\": \"3\",\n" +
                "  \"CustomsCode\": \"4218\",\n" +
                "  \"ExamAddrCode\": \"1444\"\n" +
                "}");

        EditVO editVO = WarnEntity2EditVOTransfer.transfer(warnEntity);
        Assert.assertEquals(editVO.getJobMasterId(),warnEntity.getJobMasterId());
    }

}