package com.twx.db.param;

import lombok.Data;

import java.util.List;

@Data
public class MonitorQueryParam {
    private String warningTitle;
    private List<String> warningTypes;
    private List<Boolean> activateLists;
    private int offset;
    private int size;

}
