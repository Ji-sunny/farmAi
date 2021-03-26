package com.farmai.ControllerRest;


import com.farmai.DTO.FileStorage;
import com.farmai.Service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
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

//        if (csvFile == null || csvFile.isEmpty()) {
//            throw new RuntimeException("엑셀파일이 비어있습니다.");
//        }
        try {
            if (csvFile == null || csvFile.isEmpty()) {
                throw new RuntimeException("엑셀파일이 비어있습니다.");
            }
            String csvName = csvFile.getOriginalFilename();
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
            System.out.println("filesName : " + filesName);
            System.out.println("csvName : " + csvName.substring(0, csvName.length() - 4));


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


            if(filesName.equals("farmingboxdata")){
                fileStorage = new FileStorage(filesName, csvName.substring(0, csvName.length() - 4));
                eService.insertTableFileName(fileStorage);
                Map<String, String> farmmap = new HashMap<>();
                farmmap.put("tablename", filesName);
                List<String> list = new ArrayList<>();
                for (int i = 1; i < totalList.size(); i++) {
                    int lens = totalList.get(i).size();
                    String queryInsert = "";
                    for (int j = 0; j < lens; j++) {
                        queryInsert += "\'"+totalList.get(i).get(j).replace("\"", "").replace("\u0000", "")+"\'" + ", ";
                    }
                    queryInsert = queryInsert + "\'" + csvName.substring(0, csvName.length() - 4) + "\'";
                    farmmap.put("val", queryInsert);
                    eService.insertVal(farmmap);
                }
            }else{
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
                System.out.println("dbTableName : " + dbTableName);

                ///////db에 csv 파일 구조가 긑은 table 없으면 테이블 생성///////////
                Map<String, String> map = new HashMap<>();
                if (dbTableName == null) {
                    /////////////////table 생성//////////////////////
                    String queryTable = "create table " + filesName + " (";
                    for (int i = 0; i < totalList.get(0).size(); i++) {
                        queryTable += totalList.get(0).get(i).replace("\"", "") + " CLOB, ";
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
            Map<String, Object> result = new HashMap<>();
            result.put("result","succ");
            entity = handleSuccess(result);
            System.out.println("업로드 완료");
        }catch (RuntimeException e){
            entity = handleException(e.getMessage());
        }

        return entity;
    }


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
    private ResponseEntity<Map<String, Object>> handleException(String e) {
        logger.error("예외 발생 : ", e);
        System.out.println("예외 발생 : "+e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("datastr", e);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
