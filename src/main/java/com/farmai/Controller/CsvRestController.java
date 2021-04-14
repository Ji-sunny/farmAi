package com.farmai.Controller;


import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Pager;
import com.farmai.Service.CsvService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("csv")
public class CsvRestController {

    private static final Logger logger = LoggerFactory.getLogger(CsvRestController.class);

    @Autowired
    private CsvService cService;

    private FileStorage fileStorage;


    @Resource(name = "uploadPath")
    String uploadPath;


    @PostMapping(value = "csvUpload")
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
//            ///////저장된 csv 파일있는지 체크, 있으면 저장 안되고 없으면 저장 진행///////////
//            File f = new File(direction);
//            if (f.exists()) {
//                f.delete();
//            }
//            ///////csv 파일 저장///////////
//            try {
//                csvFile.transferTo(Paths.get(direction));
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage(), e);
//            }

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
                cService.insertTableFileName(fileStorage);
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
                    cService.insertVal(farmmap);
                }
            } else {
                ///////db에 csv 파일 구조와 같은 테이블 있는지 확인///////////
                String inValue = "";
                String inValueBe = "";
                int size = totalList.get(0).size();
                for (int i = 0; i < totalList.get(0).size(); i++) {
                    inValueBe = totalList.get(0).get(i).replace("\"", "").toUpperCase();
                    if (inValueBe.charAt(0) == '_') inValueBe = inValueBe.substring(1, inValueBe.length());
                    inValue += "\'" + inValueBe + "\'" + ", ";
                }
                inValue = inValue.substring(0, inValue.length() - 2);
                Map<String, Object> maps = new HashMap<>();
                System.out.println("inValue : " + inValue);
                System.out.println("size : " + size);
                maps.put("inval", inValue);
                maps.put("size", size);
                String dbTableName = cService.selectTname(maps);
                System.out.println("dbTableName :  if전 null나오면 테이블 없음 : " + dbTableName);

                ///////db에 csv 파일 구조가 긑은 table 없으면 테이블 생성///////////
                Map<String, String> map = new HashMap<>();
                if (dbTableName == null) {
                    /////////////////table 생성//////////////////////
                    String str = "";
                    String queryTable = "create table " + filesName + " (";
                    for (int i = 0; i < totalList.get(0).size(); i++) {
                        str = totalList.get(0).get(i).replace("\"", "").toUpperCase();
                        if (str.charAt(0) == '_') str = str.substring(1, str.length());
                        queryTable += str + " VARCHAR2(50), ";
                    }
                    queryTable = queryTable + "FILES_NAME VARCHAR2(50) "
                            + "constraint " + filesName + "_fk_cascade"
                            + " references FILE_STORAGE"
                            + " on delete cascade"
                            + ")";

                    map.put("create_table", queryTable);
                    cService.createTable(map);
                    map.clear();
                    dbTableName = filesName;
                }
                ////////////////////////////////////////////////////////
                fileStorage = new FileStorage(dbTableName, csvName.substring(0, csvName.length() - 4));
                cService.insertTableFileName(fileStorage);
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
                    cService.insertVal(map);
                }
            }

            ///////////////파이썬 연결 //////////////////////

            BufferedReader in = null;
            URL obj = new URL("http://127.0.0.1:8082/preprocess?table=" + filesName.toUpperCase() + "&file=" + csvName.substring(0, csvName.length() - 4)); // 호출할 url
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String str = null;
            StringBuffer buff = new StringBuffer();
            while ((str = in.readLine()) != null) {
                buff.append(str + "\n");
            }
            String data = buff.toString().trim();

            System.out.println("data : " + data);

            JSONParser jsonParser = new JSONParser();
            JSONObject json = (JSONObject) jsonParser.parse(data);

            if (!json.get("result").equals("success") || data.equals("")) {
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

    @GetMapping("delete/{csvexcel}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("csvexcel") String csvexcel) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        String filename = csvexcel;
//        String location = uploadPath + filename + ".csv";
//        File file = new File(location);
//        if (file.exists()) {
//            if (file.delete()) {
//                System.out.println("파일삭제 성공");
//            } else {
//                System.out.println("파일삭제 실패");
//            }
//        } else {
//            System.out.println("파일이 존재하지 않습니다.");
//        }
        Map<String, String> map = new HashMap<>();
        map.put("filename", "\'%" + filename.toUpperCase() + "%\'");

        try {
            int tmp = cService.deleteCsvData(map);
            result.put("tmp", tmp);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("select/list")
    public ResponseEntity<Map<String, Object>> getListOri() {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();
        try {
            List<FileStorage> list = cService.getFileNameListOri();
            result.put("list", list);
            entity = handleSuccess(result);
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }


    @RequestMapping("save/{tableName}")
    public ResponseEntity<String> getTotalList(@PathVariable("tableName") String tableName) {
        Map<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        List<Map<String, String>> list = cService.getTableDataList(map); // DB에서 가져온 데이터리스트

        SimpleDateFormat fmt = new SimpleDateFormat("yyMMdd");
        Date dt = new Date();
        String time = fmt.format(dt);

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=MS949");
        header.add("Content-Disposition", "attachment; filename=\"" + tableName + "_" + time + ".csv" + "\"");
        return new ResponseEntity<String>(setContent(list), header, HttpStatus.CREATED);
    }

    public String setContent(List<Map<String, String>> list) {
        StringBuilder str = new StringBuilder();
        for (String a : list.get(0).keySet()) {
            str.append(a).append(",");
            str.append(" ");
        }
        str.delete(str.length() - 2, str.length());
        str.append("\n");


        for (int i = 0; i < list.size(); i++) {
            for(int k =0; k<list.get(i).values().size(); k++){
                String row = String.valueOf(list.get(i).values());
                str.append(row.replace("[","").replace("]",""));
                str.append("\n");
            }

        }
        return str.toString();
    }


    @GetMapping("table/{tName}")
    public ResponseEntity<Map<String, Object>> getTable(@PathVariable("tName")String tName) {
        ResponseEntity<Map<String, Object>> entity = null;
        Map<String, Object> result = new HashMap<>();

        Map<String, String> map = new HashMap<>();
        map.put("tableName", tName);

        try {
            List<Map<String, String>> list = cService.getTableDataList(map);

            result.put("list", list);

            System.out.println(list);


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
