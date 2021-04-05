package com.farmai.ControllerRest;


import com.farmai.DTO.FileStorage;
import com.farmai.Service.CsvService;
import com.farmai.Service.ProcessService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
