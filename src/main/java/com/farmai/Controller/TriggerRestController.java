package com.farmai.Controller;


import com.farmai.DTO.Trigger;
import com.farmai.Service.TriggerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@RestController
@RequestMapping("trigger")
public class TriggerRestController {

    private static final Logger logger = LoggerFactory.getLogger(TriggerRestController.class);

    private Trigger trigger;

    @Autowired
    TriggerService tService;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> macroCreate(@RequestParam(value = "checkX[]") List<String> checkX,
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
        trigger = new Trigger(checkTable, marcoModelName, macroName, colsX,checkY.get(0));
        try {
            int tmp = tService.saveMacro(trigger);
            result.put("result",tmp);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("dataSearch")
    public ResponseEntity<Map<String, Object>> dataSearch(@RequestParam(value = "checkX[]") List<String> checkX,
                                                            @RequestParam(value = "dataSearchModelName") String dataSearchModelName,
                                                            @RequestParam(value = "checkTable") String checkTable) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8082/pre_search";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model_name", dataSearchModelName);
        jsonObject.put("table_name", checkTable);
        jsonObject.put("cols_X", checkX);
        try {
            HttpEntity<String> entityPy = new HttpEntity<String>(jsonObject.toString(),headers);
            String answer = restTemplate.postForObject(url, entityPy, String.class);

            JSONArray jarray = (JSONArray) JSONValue.parse(answer);
            result.put("lists", jarray);

            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("modelName/{mName}")
    public ResponseEntity<Map<String, Object>> getmodelName(@PathVariable("mName")String mName) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("mName","\'"+mName+"\'");
        try {
            trigger = tService.getModelName(map);
            result.put("list", trigger);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("select/list")
    public ResponseEntity<Map<String, Object>> getTrigger() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<Trigger> list = tService.getMacroTable();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
        }
        return entity;
    }
    @GetMapping("delete/{macroname}")
    public ResponseEntity<Map<String, Object>> deleteMacro(@PathVariable("macroname") String macroname) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("macroname", macroname);
        try {
                int tmp = tService.deleteMacroTable(macroname);
//                System.out.println("GetMapping " +tmp);
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
        logger.error("?????? ?????? : ", e);
        System.out.println("?????? ?????? : " + e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("data", e.getMessage());
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> handleException(String e) {
        logger.error("?????? ?????? : ", e);
        System.out.println("?????? ?????? : " + e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("datastr", e);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
