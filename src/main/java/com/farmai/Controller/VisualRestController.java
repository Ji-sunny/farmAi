package com.farmai.Controller;

import com.farmai.DTO.MacroDone;
import com.farmai.Service.VisualService;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("visual")
public class VisualRestController {

    private static final Logger logger = LoggerFactory.getLogger(VisualRestController.class);

    @Autowired
    private VisualService vService;


    @GetMapping("evaluationList")
    public ResponseEntity<Map<String, Object>> getevaluationList() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<MacroDone> list = vService.getevaluationList();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }


    @GetMapping("evaluationList/{macroName}")
    public ResponseEntity<Map<String, Object>> getevaluationListT(@PathVariable("macroName")String macroName) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        Map<String, String>map = new HashMap<>();

        try {
            map.put("macroName","\'"+macroName+"\'");
            MacroDone macroDone = vService.getevaluationList(map);

            String score = macroDone.getScore();
            if(score.charAt(0) =='.'){
                score = "0"+score;
                macroDone.setScore(score);
            }

            JSONArray jarray = (JSONArray) JSONValue.parse(macroDone.getReport());

            result.put("list", macroDone);
            result.put("lists", jarray);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    private ResponseEntity<Map<String, Object>> handleSuccess(Map<String, Object> data) {
        data.put("status", true);
        return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
    }


    private ResponseEntity<Map<String, Object>> handleException(Exception e) {
        logger.error("예외 발생 : ", e);
        System.out.println("예외 발생 : " + e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("data", e.getMessage());
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> handleException(String e) {
        logger.error("예외 발생 : ", e);
        System.out.println("예외 발생 : " + e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("datastr", e);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
