package com.farmai.Repository;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Macro;
import com.farmai.DTO.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MacroRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public int saveMacro(Macro macro){
        int tmp = sst.insert("macro.saveMacro",macro);
        return tmp;
    }

    public Macro getModelName(Map<String, String> map) {
        Macro macro = sst.selectOne("macro.getModelName",map);
        return macro;
    }

    public List<Macro> getMacro() {
        List<Macro> list = sst.selectList("macro.getMacro");
        return list;
    }

    public int deleteMacro(String macroname) {
        int tmp = sst.delete("macro.deleteMacro", macroname);
        return tmp;
        }
}
