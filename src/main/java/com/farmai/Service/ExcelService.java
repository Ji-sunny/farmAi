package com.farmai.Service;

import com.farmai.Repository.CtableRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ExcelService {
    @Resource
    private CtableRepo tableRepo;

    public void createTable(Map<String, String>map){
        tableRepo.createTable(map);
    }
    public void insertVal(Map<String, String>map){
        tableRepo.insertVal(map);
    }
}
