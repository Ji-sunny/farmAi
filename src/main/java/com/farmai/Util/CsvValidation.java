package com.farmai.Util;

import com.farmai.Service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvValidation {

    private final CsvService eService;
    public CsvValidation(CsvService eService){
        this.eService = eService;
    }

    public static String checkDbTableName(String dbTableName,String filesName,List<List<String>> totalList){
        Map<String,String> map = new HashMap<>();
        if(dbTableName == null){
            /////////////////table 생성//////////////////////
            String queryTable ="create table "+filesName+" (";
            for(int i =0; i<totalList.get(0).size(); i++){
                queryTable += totalList.get(0).get(i).replace("\"","")+" CLOB, ";
            }
            queryTable = queryTable.substring(0, queryTable.length()-2)+")";
            map.put("create_table",queryTable);
//            eService.createTable(map);
            return dbTableName;
        }
        return dbTableName;
    }
}
