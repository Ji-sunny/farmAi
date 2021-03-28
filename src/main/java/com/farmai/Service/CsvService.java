package com.farmai.Service;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import com.farmai.Repository.CsvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CsvService {
    @Autowired
    private CsvRepo tableRepo;

    public void createTable(Map<String, String> map) {
        tableRepo.createTable(map);
    }

    public void insertVal(Map<String, String> map) {
        tableRepo.insertVal(map);
    }

    public String selectTname(Map<String, Object> map) {
        String tmp = tableRepo.selectTname(map);
        return tmp;
    }

    public void insertTableFileName(FileStorage fileStorage) {
        int tmp = tableRepo.insertTableFileName(fileStorage);
    }

    public void deleteCsvData(Map<String, String> map) {
        int tmp = tableRepo.deleteCsvData(map);
        System.out.println(tmp);
    }

    public List<FileStorage> getFileNameList() {
        return tableRepo.getFileNameList();
    }

    public int getTotalRows(Map<String, String> map) {
        int total = tableRepo.getTotalRows(map);
        return total;
    }

    public List<String> getTableList(Pager pager){
        List<String> list = tableRepo.getTableList(pager);
        return list;
    }

}
