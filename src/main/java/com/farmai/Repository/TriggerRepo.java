package com.farmai.Repository;

import com.farmai.DTO.Trigger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TriggerRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public int saveMacro(Trigger trigger) {
        int tmp = sst.insert("trigger.saveMacro", trigger);
        return tmp;
    }

    public Trigger getModelName(Map<String, String> map) {
        Trigger trigger = sst.selectOne("trigger.getModelName", map);
        return trigger;
    }

    public List<Trigger> getMacro() {
        List<Trigger> list = sst.selectList("trigger.getMacro");
        return list;
    }

    public int deleteMacro(String macroname) {
        int tmp = sst.delete("trigger.deleteMacro", macroname);
        return tmp;
    }
}
