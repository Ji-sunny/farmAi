package com.farmai.Controller;

import com.farmai.Repository.CtableRepo;
import com.farmai.Service.ExcelService;
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

    @Resource
    private ExcelService eService;

    @Resource(name="uploadPath")
    String uploadPath;


    @ResponseBody
    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST)
    public ModelAndView excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
        System.out.println("업로드 진행");
        MultipartFile excelFile = request.getFile("excelFile");

        if(excelFile == null || excelFile.isEmpty()) {
            throw new RuntimeException("엑셀파일을 선택해 주세요");
        }

        String fileName = excelFile.getOriginalFilename();
//        File destFile = new File(uploadPath,fileName);


        String direction = uploadPath+fileName;

        try {
            //내가 설정한 위치에 내가 올린 파일을 만들고
            excelFile.transferTo(Paths.get(direction));
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }

        int result = 0;
        //반환용 리스트
        List<List<String>> totalList = new ArrayList<List<String>>();
        BufferedReader br = Files.newBufferedReader(Paths.get(direction), Charset.forName("UTF-8"));

        List<String> valueList = new ArrayList<String>();

        String line = "";

        //전체 리스트
        while((line = br.readLine()) != null){
            //CSV 1행을 저장하는 리스트
            String regex = ",";
            int limit = -1;
            String column[] = line.split(regex, limit); //컬럼
            //배열에서 리스트 반환
            if(line.equals("")) {
                line = " ";
            }
            valueList = Arrays.asList(column);
            totalList.add(valueList);
        }
//        System.out.println(totalList.get(0));

//        System.out.println();
        String cTable ="create table "+fileName.substring(0,fileName.length()-4)+" (";
        int len =totalList.get(0).size();
        for(int i =0; i<len; i++){
            cTable += totalList.get(0).get(i).replace("\"","")+" CLOB, ";
//            System.out.println(totalList.get(0).get(i));
        }
        cTable = cTable.substring(0, cTable.length()-2)+")";
//        System.out.println(cTable);
        Map<String,String> map = new HashMap<String,String> ();
        map.put("create_table",cTable);
        eService.createTable(map);
        map.clear();
        map.put("tablename",fileName.substring(0,fileName.length()-4));
        for(int i =1; i<totalList.size(); i++){
            int lens = totalList.get(i).size();
            String str="";
            for(int j =0; j<lens; j++){

                str +=totalList.get(i).get(j).replace("\"","\'")+", ";
            }
            str = str.substring(0,str.length()-2);
            System.out.println(str);
            map.put("val", str);
            eService.insertVal(map);
            System.out.println();
//            System.out.println(totalList.get(i).toString());
        }



        ModelAndView view = new ModelAndView();
        view.setViewName("index");

        return view;
    }


}
