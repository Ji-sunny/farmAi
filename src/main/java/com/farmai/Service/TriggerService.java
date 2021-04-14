package com.farmai.Service;

import com.farmai.DTO.Trigger;
import com.farmai.Repository.TriggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TriggerService {
    @Autowired
    private TriggerRepo tRepo;

    public int saveMacro(Trigger trigger){
        int tmp = tRepo.saveMacro(trigger);
        return tmp;
    }

    public Trigger getModelName(Map<String, String> map) {
        return tRepo.getModelName(map);
    }

    public List<Trigger> getMacroTable() {
        return tRepo.getMacro();
    }
    public int deleteMacroTable(String macroname) {
        int tmp = tRepo.deleteMacro(macroname);
        return tmp;
    }
}
