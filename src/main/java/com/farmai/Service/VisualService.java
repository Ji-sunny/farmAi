package com.farmai.Service;


import com.farmai.DTO.MacroDone;
import com.farmai.Repository.ProcessRepo;
import com.farmai.Repository.VisualRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class VisualService {

    @Autowired
    private VisualRepo vRepo;

    public List<MacroDone> getevaluationList() {
        return vRepo.getevaluationList();
    }
    public MacroDone getevaluationList(Map<String, String> map) {
        return vRepo.getevaluationList(map);
    }


}
