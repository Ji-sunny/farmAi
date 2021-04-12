package com.farmai.Repository;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.MacroDone;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VisualRepo {
    @Autowired
    private SqlSessionTemplate sst;

    public List<MacroDone> getevaluationList() {
        return sst.selectList("macroDone.getevaluationList");
    }
    public MacroDone getevaluationList(Map<String, String> map) {
        return sst.selectOne("macroDone.getevaluationListT", map);
    }

}
