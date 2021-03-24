package com.farmai.Controller;

import com.farmai.Service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class CsvController {

    private static final Logger logger = LoggerFactory.getLogger(CsvController.class);

    @Autowired
    private CsvService eService;

    @Resource(name="uploadPath")
    String uploadPath;


    @ResponseBody
    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST)
    public ModelAndView excelUploadAjax(MultipartHttpServletRequest request) throws  Exception{
        System.out.println("업로드 진행");

        MultipartFile excelFile = request.getFile("excelFile");
        if(excelFile == null || excelFile.isEmpty()) {
            throw new RuntimeException("엑셀파일을 선택해 주세요");
        }

        String fileName = excelFile.getOriginalFilename();
        String direction = uploadPath+fileName;

        try {
            excelFile.transferTo(Paths.get(direction));
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }

        List<List<String>> totalList = new ArrayList<>();
        BufferedReader br = Files.newBufferedReader(Paths.get(direction), Charset.forName("UTF-8"));
        List<String> valueList = new ArrayList<>();

        String line = "";

        while((line = br.readLine()) != null){
            String regex = ",";
            int limit = -1;
            String column[] = line.split(regex, limit);
            if(line.equals("")) {
                line = " ";
            }
            valueList = Arrays.asList(column);
            totalList.add(valueList);
        }

        String inValue="";
        int size = totalList.get(0).size();
        for(int i =0; i<totalList.get(0).size(); i++){
            inValue += totalList.get(0).get(i).replace("\"","\'")+", ";
        }
        inValue = inValue.substring(0, inValue.length()-2);
        Map<String, Object> maps= new HashMap<>();
        maps.put("inval", inValue);
        maps.put("size", size);
        System.out.println(inValue);
        System.out.println(size);
        String dbTableName = eService.selectTname(maps);
        System.out.println(dbTableName);

        Map<String,String> map = new HashMap<> ();
        if(dbTableName == null){    ///table 없으면 테이블 생성
            /////////////////table 생성//////////////////////
            String queryTable ="create table "+fileName.substring(0,fileName.length()-4)+" (";
            for(int i =0; i<totalList.get(0).size(); i++){
                queryTable += totalList.get(0).get(i).replace("\"","")+" CLOB, ";
            }
            queryTable = queryTable.substring(0, queryTable.length()-2)+")";
            map.put("create_table",queryTable);
            eService.createTable(map);
            map.clear();
        }

        /////////////////table data insert//////////////////////
        map.put("tablename",dbTableName);
        List<String>list = new ArrayList<>();
        for(int i =1; i<totalList.size(); i++){
            int lens = totalList.get(i).size();
            String queryInsert="";
            for(int j =0; j<lens; j++){
                queryInsert +=totalList.get(i).get(j).replace("\"","\'")+", ";
            }
            queryInsert = queryInsert.substring(0,queryInsert.length()-2);

            map.put("val", queryInsert);
            eService.insertVal(map);
        }
//        for(int i =0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }
//        map.put("val",list);
//
//        eService.insertVal(map);
        System.out.println("업로드 완료");
        ModelAndView view = new ModelAndView();
        view.setViewName("index");

        return view;
    }


}
