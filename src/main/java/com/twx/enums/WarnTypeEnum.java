package com.twx.enums;

import lombok.Getter;

@Getter
public enum WarnTypeEnum {

    DOING("WaitDoingTask","预警待办"),
    READING("WaitReadingTask","预警提示");

    private String type;
    private String description;

    WarnTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public static boolean isWarnType(String type){
        for (WarnTypeEnum e : WarnTypeEnum.values()){
            if (e.getType().equals(type))
                return true;
        }
        return false;
    }
}
