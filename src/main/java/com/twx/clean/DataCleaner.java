package com.twx.clean;

import com.google.gson.Gson;

import java.util.*;

public class DataCleaner {

    /**
     * 将预警类型、日期、值组成的表数据转换为 echarts Line能接收的数据格式
     */
    public static Map<String, Object> toEchartsLine(List<Map<String, String>> mapList) {

        if (mapList != null && mapList.size() > 0) {

            /**
             * 预警待办的数据格式：
             * 期望：  Doing:{date:[],value[]}
             * 所以这里的List 代表的是 date数组和value数组
             */
            List<String> doingDateList = new LinkedList<>();
            List<String> doingValues = new LinkedList<>();

            //解释同上
            List<String> readingDateList = new LinkedList<>();
            List<String> readingValues = new LinkedList<>();

            //把值取出放入到list中
            for (Map<String, String> line : mapList) {
                if (line.get("warningType").equals("WaitDoingTask")) {
                    doingDateList.add(line.get("date"));
                    doingValues.add(line.get("count"));
                }
                if (line.get("warningType").equals("WaitReadingTask")) {
                    readingDateList.add(line.get("date"));
                    readingValues.add(line.get("count"));
                }
            }

            int doingSize = doingDateList.size();
            int readingSize = readingDateList.size();

            //说明 reading数组 比 doing数组 短，要填充reading数组
            if (doingSize > readingSize) {
                fillList(readingDateList, readingValues, doingDateList, doingSize);
            } else if (readingSize > doingSize) {
                fillList(doingDateList, doingValues, readingDateList, readingSize);
            }

            Map<String, List<String>> doingMap = new HashMap<>();
            doingMap.put("date", doingDateList);
            doingMap.put("value", doingValues);

            Map<String, List<String>> readingMap = new HashMap<>();
            readingMap.put("date", readingDateList);
            readingMap.put("value", readingValues);

            /**至此，以拼凑完成如下格式的数据  {date:[],value[]}  */

            Map<String, Object> outerMap = new HashMap<>();
            outerMap.put("Doing", doingMap);
            outerMap.put("Reading", readingMap);

            /**至此，以拼凑完成如下格式的数据
             * Doing:{date:[],value[]}
             * Reading: {date:[],value[]}
             * */
            return outerMap;
        }// end if

        return  null;
    }

    private static void fillList(List<String> targetDateList, List<String> targetValues, List<String> sourceDateList, int sourceDateListSize) {
        for (int i=0;i<sourceDateListSize;i++) {
            String date = sourceDateList.get(i);
            if (!targetDateList.contains(date)) {
                targetDateList.add(i, date);
                targetValues.add(i,"0");
            }
        }
    }
}
