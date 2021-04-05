package com.farmai.Repository;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProcessRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public List<String> getFileNameList() {
        return sst.selectList("process.getFileNameList");
    }
    public List<FileStorage> getFileNameListNew() {
        return sst.selectList("process.getFileNameListNew");
    }
    public List<Map<String, String>> getColumnNames(Map<String, String>map) {
        return sst.selectList("process.getColumnNames",map);
    }
}
