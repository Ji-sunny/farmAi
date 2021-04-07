package com.farmai.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("macro")
public class MacroRestController {

    private static final Logger logger = LoggerFactory.getLogger(MacroRestController.class);

    @GetMapping("create")
    public ResponseEntity<Map<String, Object>> macroCreate(HttpServletRequest req) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        String[] checkX = req.getParameterValues("checkX");
        String[] checkY = req.getParameterValues("checkY");
        String marcoModelName = req.getParameter("marcoModelName");
        String checkTable = req.getParameter("checkTable");
        String macroName = req.getParameter("macroName");
        System.out.println(checkX);
        System.out.println(checkY);
        System.out.println(marcoModelName);
        System.out.println(checkTable);
        System.out.println(macroName);
        try {
//            map.put("tableName","\'"+tableName.toUpperCase()+"\'");
//            List<Map<String, String>> list = eService.getColumnNames(map);
//            result.put("list", list);

            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @PostMapping("create2")
    public ResponseEntity<Map<String, Object>> macroCreate2(@RequestParam(value = "checkX[]") List<String> checkX,
                                                            @RequestParam(value = "checkY[]") List<String> checkY,
                                                            @RequestParam(value = "marcoModelName") String marcoModelName,
                                                            @RequestParam(value = "checkTable") String checkTable,
                                                            @RequestParam(value = "macroName") String macroName) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
//        String[] checkX = req.getParameterValues("checkX");
//        String[] checkY = req.getParameterValues("checkY");
//        String marcoModelName = req.getParameter("marcoModelName");
//        String checkTable = req.getParameter("checkTable");
//        String macroName = req.getParameter("macroName");
        System.out.println(checkX);
        System.out.println(checkY);
        System.out.println(checkTable);
        System.out.println(macroName);
        System.out.println(marcoModelName);

        try {
            result.put("result","success");
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("columnList/{tableName}")
    public ResponseEntity<Map<String, Object>> columnList(@PathVariable("tableName") String tableName) {
        System.out.println("tableName : "+tableName);
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        Map<String, String>map = new HashMap<>();
        try {
//            map.put("tableName","\'"+tableName.toUpperCase()+"\'");
//            List<Map<String, String>> list = eService.getColumnNames(map);
//            result.put("list", list);

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
