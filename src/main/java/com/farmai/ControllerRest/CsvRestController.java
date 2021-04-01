package com.farmai.ControllerRest;


import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Macro;
import com.farmai.DTO.Pager;
import com.farmai.Service.CsvService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class CsvRestController {

    private static final Logger logger = LoggerFactory.getLogger(CsvRestController.class);

    @Autowired
    private CsvService eService;

    private FileStorage fileStorage;


    @Resource(name = "uploadPath")
    String uploadPath;


    @PostMapping(value = "/csvUpload")
    public ResponseEntity<Map<String, Object>> excelUpload(MultipartHttpServletRequest request) throws Exception {
        System.out.println("업로드 진행");
        ResponseEntity<Map<String, Object>> entity = null;

        MultipartFile csvFile = request.getFile("csvFile");

        try {
            if (csvFile == null || csvFile.isEmpty()) {
                throw new RuntimeException("엑셀파일이 비어있습니다.");
            }
            String csvName = csvFile.getOriginalFilename().toUpperCase();
            String filesName = csvName.substring(0, csvName.length() - 4);
            String direction = uploadPath + csvName;
            ///////저장된 csv 파일있는지 체크, 있으면 저장 안되고 없으면 저장 진행///////////
            File f = new File(direction);
            if (f.exists()) {
                throw new RuntimeException("같은 이름의 엑셀파일이 존재 합니다");
            }

            ///////csv 파일 저장///////////
            try {
                csvFile.transferTo(Paths.get(direction));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            ///////file_storage에 테이블 이름과 csv파일이름 저장///////////

            filesName = filesName.replaceAll("[^a-zA-Z\\s]", "").trim();
//            System.out.println("filesName : " + filesName);
//            System.out.println("csvName : " + csvName.substring(0, csvName.length() - 4));


            ///////csv 파일 읽기///////////
            List<List<String>> totalList = new ArrayList<>();
            List<String> valueList = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(Paths.get(direction), Charset.forName("UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String regex = ",";
                int limit = -1;
                String column[] = line.split(regex, limit);
                if (line.equals("")) {
                    line = " ";
                }
                valueList = Arrays.asList(column);
                totalList.add(valueList);
            }


            if (filesName.equals("farmingboxdata")) {
                fileStorage = new FileStorage(filesName, csvName.substring(0, csvName.length() - 4));
                eService.insertTableFileName(fileStorage);
                Map<String, String> farmmap = new HashMap<>();
                farmmap.put("tablename", filesName);
                List<String> list = new ArrayList<>();
                for (int i = 1; i < totalList.size(); i++) {
                    int lens = totalList.get(i).size();
                    String queryInsert = "";
                    for (int j = 0; j < lens; j++) {
                        queryInsert += "\'" + totalList.get(i).get(j).replace("\"", "").replace("\u0000", "") + "\'" + ", ";
                    }
                    queryInsert = queryInsert + "\'" + csvName.substring(0, csvName.length() - 4) + "\'";
                    farmmap.put("val", queryInsert);
                    eService.insertVal(farmmap);
                }
            } else {
                ///////db에 csv 파일 구조와 같은 테이블 있는지 확인///////////
                String inValue = "";
                int size = totalList.get(0).size();
                for (int i = 0; i < totalList.get(0).size(); i++) {
                    inValue += totalList.get(0).get(i).replace("\"", "\'") + ", ";
                }
                inValue = inValue.substring(0, inValue.length() - 2);
                Map<String, Object> maps = new HashMap<>();
                maps.put("inval", inValue);
                maps.put("size", size);
                String dbTableName = eService.selectTname(maps);
                System.out.println("dbTableName :  if전 null나오면 테이블 없음 : " + dbTableName);

                ///////db에 csv 파일 구조가 긑은 table 없으면 테이블 생성///////////
                Map<String, String> map = new HashMap<>();
                if (dbTableName == null) {
                    /////////////////table 생성//////////////////////
                    String queryTable = "create table " + filesName + " (";
                    for (int i = 0; i < totalList.get(0).size(); i++) {
                        queryTable += totalList.get(0).get(i).replace("\"", "") + " VARCHAR2(50), ";
                    }
                    queryTable = queryTable + "FILES_NAME VARCHAR2(50) "
                            + "constraint " + filesName + "_fk_cascade"
                            + " references FILE_STORAGE"
                            + " on delete cascade"
                            + ")";

                    map.put("create_table", queryTable);
                    eService.createTable(map);




                    map.clear();
                    dbTableName = filesName;
                }
                ////////////////////////////////////////////////////////
                fileStorage = new FileStorage(dbTableName, csvName.substring(0, csvName.length() - 4));
                eService.insertTableFileName(fileStorage);
                /////////////////table data insert//////////////////////
                map.put("tablename", dbTableName);
                List<String> list = new ArrayList<>();
                for (int i = 1; i < totalList.size(); i++) {
                    int lens = totalList.get(i).size();
                    String queryInsert = "";
                    for (int j = 0; j < lens; j++) {
                        queryInsert += totalList.get(i).get(j).replace("\"", "\'") + ", ";
                    }
                    queryInsert = queryInsert + "\'" + csvName.substring(0, csvName.length() - 4) + "\'";
                    map.put("val", queryInsert);
                    eService.insertVal(map);
                }
            }

            ///////////////파이썬 연결 //////////////////////

            BufferedReader in = null;
            URL obj = new URL("http://127.0.0.1:8082/preprocess?table="+filesName.toUpperCase()+"&file="+csvName.substring(0, csvName.length() - 4)); // 호출할 url
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();

            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String str = null;
            StringBuffer buff = new StringBuffer();
            while ((str = in.readLine()) != null) {
                buff.append(str + "\n");
            }
            String data = buff.toString().trim();

            System.out.println("data : "+data);

            JSONParser jsonParser = new JSONParser();
            JSONObject json = (JSONObject) jsonParser.parse(data);

            if(!json.get("result").equals("success") || data.equals("")){
                throw new RuntimeException((String) json.get("result"));
            }
            /////////////////////////////////////////////

            Map<String, Object> result = new HashMap<>();
            result.put("result", "success");
            entity = handleSuccess(result);
            System.out.println("업로드 완료");
        } catch (RuntimeException e) {
            entity = handleException(e.getMessage());
        }

        return entity;
    }


    @GetMapping("select/list")
    public ResponseEntity<Map<String, Object>> getList() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<String> list = eService.getFileNameList();
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
            List<FileStorage> list = eService.getFileNameListNew();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }
    @GetMapping("selectori/list")
    public ResponseEntity<Map<String, Object>> getListOri() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<FileStorage> list = eService.getFileNameListOri();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }


//    @GetMapping("table/list")
//    public ResponseEntity<Map<String, Object>> getTable(@RequestParam(defaultValue = "1") int pageNo, @RequestParam String tableName, @RequestParam (defaultValue = "10")int rowsPer, ModelAndView mv) throws IOException {
//
//        ResponseEntity<Map<String, Object>> entity = null;
//        Map<String, String> map = new HashMap<>();
//
//        try {
//            map.put("tableName", tableName);
//            int totalRows = eService.getTotalR
//            ows(map);
//            Pager pager = new Pager(rowsPer, 5, totalRows, pageNo, tableName);
//            List<Map<String,String>> list = eService.getTableList(pager);
//
//            Map<String, Object> result = new HashMap<>();
//
//            result.put("list", list);
//            result.put("pager", pager);
/////////////////////////////////////////////////////////////
//            entity = handleSuccess(result);
//        } catch (RuntimeException e) {
//            entity = handleException(e);
//        }
//        return entity;
//    }

    @GetMapping("table/list2222")
    public ResponseEntity<Map<String, Object>> getTable222(@RequestParam(defaultValue = "1") int pageNo, @RequestParam String tableName, @RequestParam (defaultValue = "10")int rowsPer, ModelAndView mv) throws IOException {

        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, String> map = new HashMap<>();

        try {
            map.put("tableName", tableName);
            int totalRows = eService.getTotalRows(map);
            Pager pager = new Pager(rowsPer, 5, totalRows, pageNo, tableName);
            List<Map<String,String>> list = eService.getTableList(pager);

            Map<String, Object> result = new HashMap<>();

            result.put("list", list);
            result.put("pager", pager);
///////////////////////////////////////////////////////////
            List<Object> listlist = new ArrayList<>();
            listlist.add(list);
            listlist.add(pager);

            Map<String, Object> result2 = new HashMap<>();
            result2.put("data",listlist);
            entity = handleSuccess(result2);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("macro/list")
    public ResponseEntity<Map<String, Object>> getMacro() {
//        System.out.println("/macro/list");
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<Macro> list = eService.getMacroList();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("table_new/list/{tableName}")
    public ResponseEntity<Map<String, Object>> getNewList(@PathVariable("tableName") String tableName) {
        System.out.println(tableName);

        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        Map<String, String>map = new HashMap<>();
        try {
            map.put("tableName","\'"+tableName.toUpperCase()+"\'");
            List<Map<String, String>> list = eService.getColumnNames(map);
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
