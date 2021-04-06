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
    @GetMapping("merge")
    public ResponseEntity<Map<String, Object>> macroSave(HttpServletRequest req) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        String[] check1 = req.getParameterValues("check1");
        String[] check2 = req.getParameterValues("check2");
        String[] check3 = req.getParameterValues("check3");
        String[] check4 = req.getParameterValues("check4");
        String[] check5 = req.getParameterValues("check5");
        String[] check6 = req.getParameterValues("check6");
        String[] check7 = req.getParameterValues("check7");
        String[] check8 = req.getParameterValues("check8");
        String[] check9 = req.getParameterValues("check9");
        String[] check10 = req.getParameterValues("check10");
        String table1 = req.getParameter("check1name");
        String table2 = req.getParameter("check2name");
        String table3 = req.getParameter("check3name");
        String table4 = req.getParameter("check4name");
        String table5 = req.getParameter("check5name");
        ArrayList<String> tableName = new ArrayList<>();
        tableName.add(table1);
        tableName.add(table2);
        tableName.add(table3);
        tableName.add(table4);
        tableName.add(table5);
        ArrayList<String> selCol;
        ArrayList<ArrayList<String>> sel = new ArrayList<>();
        ArrayList<String> stndCol;
        ArrayList<ArrayList<String>> stnd = new ArrayList<>();

        if (check1 != null) {
            selCol = new ArrayList<>(Arrays.asList(check1));
            sel.add(selCol);
        }
        if (check2 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check2));
            stnd.add(stndCol);
        }
        if (check3 != null) {
            selCol = new ArrayList<>(Arrays.asList(check3));
            sel.add(selCol);
        }
        if (check4 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check4));
            stnd.add(stndCol);
        }
        if (check5 != null) {
            selCol = new ArrayList<>(Arrays.asList(check5));
            sel.add(selCol);
        }
        if (check6 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check6));
            stnd.add(stndCol);
        }
        if (check7 != null) {
            selCol = new ArrayList<>(Arrays.asList(check7));
            sel.add(selCol);
        }
        if (check8 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check8));
            stnd.add(stndCol);
        }
        if (check9 != null) {
            selCol = new ArrayList<>(Arrays.asList(check9));
            sel.add(selCol);
        }
        if (check10 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check10));
            stnd.add(stndCol);
        }

        System.out.println("table_name : "+tableName);
        System.out.println("sel_cols : "+sel);
        System.out.println("stnd_cols : "+stnd);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8082/mergetable";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table_names", tableName);
        jsonObject.put("sel_cols", sel);
        jsonObject.put("stnd_cols", stnd);
        try {
            HttpEntity<String> entityPy = new HttpEntity<String>(jsonObject.toString(),headers);
            String answer = restTemplate.postForObject(url, entityPy, String.class);
            System.out.println(answer);
            result.put("answer", answer);
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
