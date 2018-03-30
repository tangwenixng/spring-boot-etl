package com.twx.dto;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParamDtoTest {

    @Test
    public void test(){
        String str = "{\n" +
                "\"AlertType\": \"3\",\n" +
                "\"CustomsCode\": \"4218\",\n" +
                "  \"ExamAddrCode\": \"1444\",\n" +
                "  \"WarningThreshold\": \"80\"\n" +
                "}";
        Gson gson = new Gson();
        JsonParamDto jsonParamDto = gson.fromJson(str, JsonParamDto.class);
        System.out.println(jsonParamDto);
        String json = gson.toJson(jsonParamDto);
        System.out.println(json);
        Assert.assertNotNull(jsonParamDto);
    }

}