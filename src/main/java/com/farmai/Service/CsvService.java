package com.farmai.Service;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import com.farmai.Repository.CsvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CsvService {
    @Autowired
    private CsvRepo cRepo;

    public void createTable(Map<String, String> map) {
        cRepo.createTable(map);
    }

    public void insertVal(Map<String, String> map) {
        cRepo.insertVal(map);
    }

    public String selectTname(Map<String, Object> map) {
        String tmp = cRepo.selectTname(map);
        return tmp;
    }

    public void insertTableFileName(FileStorage fileStorage) {
        int tmp = cRepo.insertTableFileName(fileStorage);
    }

    public void deleteCsvData(Map<String, String> map) {
        int tmp = cRepo.deleteCsvData(map);
    }

    public List<FileStorage> getFileNameListOri() {
        return cRepo.getFileNameListOri();
    }

    public int getTotalRows(Map<String, String> map) {
        int total = cRepo.getTotalRows(map);
        return total;
    }

    public List<Map<String, String>> getTableList(Pager pager) {
        List<Map<String, String>> list = cRepo.getTableList(pager);
        return list;
    }

    public List<Map<String, String>> getTableDataList(Map<String, String> map) {
        List<Map<String, String>> list = cRepo.getTableDataList(map);
        return list;
    }

}
