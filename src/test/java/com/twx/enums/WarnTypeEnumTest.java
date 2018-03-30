package com.twx.enums;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarnTypeEnumTest {
    @Test
    public void isWarnType() throws Exception {
        boolean res = WarnTypeEnum.isWarnType("WaitDoingTask");
        Assert.assertNotEquals(false,res);
    }

}