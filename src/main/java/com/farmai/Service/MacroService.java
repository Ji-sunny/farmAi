package com.farmai.Service;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Macro;
import com.farmai.DTO.Pager;
import com.farmai.Repository.CsvRepo;
import com.farmai.Repository.MacroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MacroService {
    @Autowired
    private MacroRepo mRepo;

    public int saveMacro(Macro macro){
        int tmp = mRepo.saveMacro(macro);
        return tmp;
    }

    public Macro getModelName(Map<String, String> map) {
        return mRepo.getModelName(map);
    }
}
