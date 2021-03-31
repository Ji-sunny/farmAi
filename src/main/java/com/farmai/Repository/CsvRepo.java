package com.farmai.Repository;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Macro;
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
    public List<FileStorage> getFileNameListNew() {
        return sst.selectList("excel.getFileNameListNew");
    }
    public List<FileStorage> getFileNameListOri() {
        return sst.selectList("excel.getFileNameListOri");
    }

    public List<Map<String, String>> getColumnNames(Map<String, String>map) {
        return sst.selectList("excel.getColumnNames",map);
    }

    public int getTotalRows(Map<String, String> map) {
        int tmp = sst.selectOne("excel.getTotalRows", map);
        return tmp;
    }

    public List<Map<String, String>> getTableList(Pager pager) {
//        System.out.println("list : 전");
        List<Map<String, String>> list = sst.selectList("excel.getTableList", pager);
//        System.out.println("list : " + list);
        return list;
    }

    public int insertMacro(Macro macro) {
        int tmp = sst.insert("excel.insertMacro", macro);
        return tmp;
    }

    public List<Macro> getMacroList() {
        return sst.selectList("excel.getMacroList");
    }

    public int deleteMacro(Macro macro) {
        int tmp = sst.delete("excel.deleteMacro", macro);
        return tmp;
    }

    public Macro getMacro(Map<String, String> map) {
        return sst.selectOne("excel.getMacro",map);
    }
}
