package com.farmai.Controller;


import com.farmai.DTO.FileStorage;
import com.farmai.Service.ProcessService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("process")
public class ProcessRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessRestController.class);

    @Autowired
    private ProcessService pService;

    @GetMapping("select/list")
    public ResponseEntity<Map<String, Object>> getList() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<String> list = pService.getFileNameList();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }
    @GetMapping("selectnew/list")
    public ResponseEntity<Map<String, Object>> getListNew() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<FileStorage> list = pService.getFileNameListNew();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }


    @GetMapping("table_new/list/{tableName}")
    public ResponseEntity<Map<String, Object>> getNewList(@PathVariable("tableName") String tableName) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        Map<String, String>map = new HashMap<>();
        try {
            map.put("tableName","\'"+tableName.toUpperCase()+"\'");
            List<Map<String, String>> list = pService.getColumnNames(map);
            result.put("list", list);

            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }
    @GetMapping(value="/merge",produces ="application/text;charset=utf8")
    public ResponseEntity<Map<String, Object>> macroSave(HttpServletRequest req,
                                                         @RequestParam(value="check1[]", required=false) List<String> check1,
                                                         @RequestParam(value = "table1", required=false) String table1,
                                                         @RequestParam(value="check2[]", required=false) List<String> check2,
                                                         @RequestParam(value="check3[]", required=false) List<String> check3,
                                                         @RequestParam(value = "table2", required=false) String table2,
                                                         @RequestParam(value="check4[]", required=false) List<String> check4,
                                                         @RequestParam(value="check5[]", required=false) List<String> check5,
                                                         @RequestParam(value = "table3", required=false) String table3,
                                                         @RequestParam(value="check6[]", required=false) List<String> check6,
                                                         @RequestParam(value="check7[]", required=false) List<String> check7,
                                                         @RequestParam(value = "table4", required=false) String table4,
                                                         @RequestParam(value="check8[]", required=false) List<String> check8,
                                                         @RequestParam(value="check9[]", required=false) List<String> check9,
                                                         @RequestParam(value = "table5", required=false) String table5,
                                                         @RequestParam(value="check10[]", required=false) List<String> check10
    ) {

        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();

        ArrayList<String> tableName = new ArrayList<>();
        tableName.add(table1);
        tableName.add(table2);
        tableName.add(table3);
        tableName.add(table4);
        tableName.add(table5);
        ArrayList<List<String>> sel = new ArrayList<>();
        ArrayList<List<String>> stnd = new ArrayList<>();

        if (check1 != null) {
            sel.add(check1);
        }
        if (check2 != null) {
            stnd.add(check2);
        }
        if (check3 != null) {
            sel.add(check3);
        }
        if (check4 != null) {
            stnd.add(check4);
        }
        if (check5 != null) {
            sel.add(check5);
        }
        if (check6 != null) {
            stnd.add(check6);
        }
        if (check7 != null) {
            sel.add(check7);
        }
        if (check8 != null) {
            stnd.add(check8);
        }
        if (check9 != null) {
            sel.add(check9);
        }
        if (check10 != null) {
            stnd.add(check10);
        }

        System.out.println("table_name : "+tableName);
        System.out.println("sel_cols : "+sel);
        System.out.println("stnd_cols : "+stnd);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://127.0.0.1:8082/mergetable";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("table_names", tableName);
//        jsonObject.put("sel_cols", sel);
//        jsonObject.put("stnd_cols", stnd);
//        try {
//            HttpEntity<String> entityPy = new HttpEntity<String>(jsonObject.toString(),headers);
//            String answer = restTemplate.postForObject(url, entityPy, String.class);
//            System.out.println(answer);
//            result.put("answer", answer);
//            entity = handleSuccess(result);
//        } catch (RuntimeException e) {
//            entity = handleException(e);
//        }
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
