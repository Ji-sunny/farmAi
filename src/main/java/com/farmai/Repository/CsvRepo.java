package com.farmai.Repository;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CsvRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public void createTable(Map<String, String> map) {
        sst.selectOne("csv.createTable", map);
    }

    public void insertVal(Map<String, String> map) {
        sst.insert("csv.insertVal", map);
    }

    public String selectTname(Map<String, Object> map) {
        String tmp = sst.selectOne("csv.selectTname", map);
        return tmp;
    }

    public int insertTableFileName(FileStorage fileStorage) {
        int tmp = sst.insert("csv.insertTableFileName", fileStorage);
        return tmp;
    }

    public int deleteCsvData(Map<String, String> map) {
        int tmp = sst.delete("csv.deleteCsvData", map);
        return tmp;
    }

    public List<FileStorage> getFileNameListOri() {
        return sst.selectList("csv.getFileNameListOri");
    }

    public int getTotalRows(Map<String, String> map) {
        int tmp = sst.selectOne("csv.getTotalRows", map);
        return tmp;
    }

    public List<Map<String, String>> getTableList(Pager pager) {
        List<Map<String, String>> list = sst.selectList("csv.getTableList", pager);
        return list;
    }

    public List<Map<String, String>> getTableDataList(Map<String, String> map) {
        List<Map<String, String>> list = sst.selectList("csv.getTableDataList", map);
        return list;
    }

}
