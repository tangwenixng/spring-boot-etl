package com.twx.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class JsonParamDto {

    @SerializedName("AlertType")
    private String alertType;
    @SerializedName("CustomsCode")
    private String customsCode;
    @SerializedName("ExamAddrCode")
    private String examAddrCode;
    @SerializedName("WarningThreshold")
    private String warningThreshold;
}
