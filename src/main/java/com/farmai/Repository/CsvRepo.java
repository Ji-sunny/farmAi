package com.farmai.Repository;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CsvRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public void createTable(Map<String, String> map) {
        sst.selectOne("excel.createTable", map);
    }

    public void insertVal(Map<String, String> map) {
        sst.insert("excel.insertVal", map);
    }

    public String selectTname(Map<String, Object> map) {
        String tmp = sst.selectOne("excel.selectTname", map);
        return tmp;
    }

    public int insertTableFileName(FileStorage fileStorage) {
        int tmp = sst.insert("excel.insertTableFileName", fileStorage);
        return tmp;
    }

    public int deleteCsvData(Map<String, String> map) {
        int tmp = sst.delete("excel.deleteCsvData", map);
        return tmp;
    }

    public List<FileStorage> getFileNameList() {
        return sst.selectList("excel.getFileNameList");
    }

    public int getTotalRows(Map<String, String> map) {
        int tmp = sst.selectOne("excel.getTotalRows", map);
        return tmp;
    }

    public List<String> getTableList(Pager pager){
        List<String>list = sst.selectList("excel.getTableList",pager);
        return list;
    }
}
