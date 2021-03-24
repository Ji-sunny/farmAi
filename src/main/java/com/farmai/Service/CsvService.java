package com.farmai.Service;

import com.farmai.DTO.FileStorage;
import com.farmai.Repository.CsvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class CsvService {
    @Autowired
    private CsvRepo tableRepo;

    public void createTable(Map<String, String>map){
        tableRepo.createTable(map);
    }
    public void insertVal(Map<String, String>map){
        tableRepo.insertVal(map);
    }
    public String selectTname(Map<String, Object>map){
        String tmp= tableRepo.selectTname(map);
        return tmp;
    }

    public void insertTableFileName(FileStorage fileStorage){
        int tmp = tableRepo.insertTableFileName(fileStorage);
        System.out.println(tmp);
    }

}
