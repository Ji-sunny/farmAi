package com.farmai.Repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class CtableRepo {
    @Resource
    private SqlSessionTemplate sst;
    public void createTable(Map<String, String>map){
        sst.selectOne("excel.createTable", map);
    }
    public void insertVal(Map<String, String>map){
        sst.insert("excel.insertVal", map);
    }
}
