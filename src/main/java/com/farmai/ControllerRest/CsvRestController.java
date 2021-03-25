package com.farmai.ControllerRest;


import com.farmai.DTO.FileStorage;
import com.farmai.Service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class CsvRestController {

    private static final Logger logger = LoggerFactory.getLogger(CsvRestController.class);

    @Autowired
    private CsvService eService;

    @GetMapping("select/list")
    public ResponseEntity<Map<String, Object>> getList(){
        System.out.println("/select/list");
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<FileStorage>list = eService.getFileNameList();
            result.put("test",list);
            entity = handleSuccess(result);
        } catch (RuntimeException e){
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
        System.out.println("예외 발생 : "+e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("data", e.getMessage());
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
