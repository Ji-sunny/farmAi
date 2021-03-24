package com.farmai.Repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class CsvRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public void createTable(Map<String, String>map){
        sst.selectOne("excel.createTable", map);
    }
    public void insertVal(Map<String, String>map){
        System.out.println("왔음");
        System.out.println("tablename : "+map.get("tablename"));
        sst.insert("excel.insertVal", map);
    }
    public String selectTname(Map<String, Object>map){
        String tmp = sst.selectOne("excel.selectTname",map);
        return tmp;
    }

}
