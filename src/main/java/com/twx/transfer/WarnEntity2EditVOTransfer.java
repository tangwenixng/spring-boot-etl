package com.twx.transfer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twx.VO.EditVO;
import com.twx.entity.WarnEntity;
import com.twx.enums.ParamEnum;
import com.twx.enums.WarnTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class WarnEntity2EditVOTransfer {

    public static EditVO transfer(WarnEntity warnEntity) {
        EditVO editVO = new EditVO();

        BeanUtils.copyProperties(warnEntity,editVO);
        editVO.setActivated(warnEntity.getIsActivated());
        String jsonParam = warnEntity.getJsonParameter();

        try {
            JsonObject obj = new JsonParser().parse(jsonParam).getAsJsonObject();
            //将jsonParam中的提取出来
            JsonElement jsonElement = obj.get("WarningThreshold");
            if (jsonElement != null) {
                editVO.setThreshold(jsonElement.getAsInt());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return editVO;
    }
}
