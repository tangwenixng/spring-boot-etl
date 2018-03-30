package com.twx.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import java.util.*;

public class MonitorProvider {

    public String query(Map<String,Object> map){
        String title = (String) map.get("arg0");
        List<String> types = (List<String>) map.get("arg1");
        List<Boolean> activates = (List<Boolean>) map.get("arg2");
        int page = (int)map.get("arg3");
        int size = (int)map.get("arg4");
        int offset = (page-1)*size;

        String sql = "SELECT j.JobMasterID,w.WarningTitle,w.WarningType,j.IsActivated "+
                " FROM WarningTemplate w INNER join JobMaster j ON w.WarningCode = j.WarningCode "+
                "WHERE WarningTitle LIKE \'"+title+"\' and w.WarningType in ("+list2StringByComma(types)+") " +
                "and j.IsActivated in ("+boolean2List(activates)+")"+
                " ORDER BY j.JobMasterID OFFSET "+(offset)+" ROWS FETCH NEXT "+size+" ROWS ONLY";
        return  sql;

    }

    public String queryTotal(Map<String,Object> map) {
        String title = (String) map.get("arg0");
        List<String> types = (List<String>) map.get("arg1");
        List<Boolean> activates = (List<Boolean>) map.get("arg2");

        String sql = "SELECT count(*) as total "+
                " FROM WarningTemplate w INNER join JobMaster j ON w.WarningCode = j.WarningCode "+
                "WHERE WarningTitle LIKE \'"+title+"\' and w.WarningType in ("+list2StringByComma(types)+") " +
                "and j.IsActivated in ("+boolean2List(activates)+")";
        return sql;
    }

    private  String list2StringByComma(List<String> list){
        StringBuilder sb = new StringBuilder("");
        for (String str : list) {
            sb.append("\'").append(str).append("\'")
                    .append(",");
        }
        String s = sb.toString();
        return s.substring(0,s.length()-1);
    }

    private String boolean2List(List<Boolean> list) {
        StringBuilder sb = new StringBuilder("");
        for (Boolean b : list) {
            int tmp = b==true?1:0;
            sb.append(tmp)
                    .append(",");
        }

        String s = sb.toString();
        return s.substring(0,s.length()-1);
    }
}
