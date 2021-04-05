package com.farmai.Service;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import com.farmai.Repository.CsvRepo;
import com.farmai.Repository.ProcessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProcessService {
    @Autowired
    private ProcessRepo pRepo;

    public List<String> getFileNameList() {
        return pRepo.getFileNameList();
    }
    public List<FileStorage> getFileNameListNew() {
        return pRepo.getFileNameListNew();
    }
    public List<Map<String, String>> getColumnNames(Map<String, String>map){
        return pRepo.getColumnNames(map);
    }


}
