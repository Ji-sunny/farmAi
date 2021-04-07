package com.farmai.Controller;


import com.farmai.DTO.Macro;
import com.farmai.Service.MacroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("macro")
public class MacroRestController {

    private static final Logger logger = LoggerFactory.getLogger(MacroRestController.class);

    private Macro macro;

    @Autowired
    MacroService mService;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> macroCreate2(@RequestParam(value = "checkX[]") List<String> checkX,
                                                            @RequestParam(value = "checkY[]") List<String> checkY,
                                                            @RequestParam(value = "marcoModelName") String marcoModelName,
                                                            @RequestParam(value = "checkTable") String checkTable,
                                                            @RequestParam(value = "macroName") String macroName) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        String colsX= "";
        for(int i =0; i<checkX.size(); i++){
            colsX+= checkX.get(i)+",";
        }
        colsX = colsX.substring(0,colsX.length()-1);
        macro = new Macro(checkTable, marcoModelName, macroName, colsX,checkY.get(0));
        try {
            int tmp = mService.saveMacro(macro);
            result.put("result",tmp);
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
