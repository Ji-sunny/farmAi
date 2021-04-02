package com.farmai.Controller;

import com.farmai.DTO.Pager;
import com.farmai.Service.CsvService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
public class CsvController {

    private static final Logger logger = LoggerFactory.getLogger(CsvController.class);

    @Autowired
    private CsvService eService;

    @Resource(name = "uploadPath")
    String uploadPath;


    @GetMapping("delete")
    public String delete(HttpServletRequest req) {
        String filename = req.getParameter("csvexcel");
//        System.out.println(filename);
        String location = uploadPath + filename + ".csv";
//        System.out.println(location);
        File file = new File(location);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("파일삭제 성공");
            } else {
                System.out.println("파일삭제 실패");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
        Map<String, String> map = new HashMap<>();
        map.put("filename", "\'%" + filename.toUpperCase() + "%\'");
        System.out.println(filename);
        eService.deleteCsvData(map);

        return "redirect:/";
    }

    @GetMapping("modeling")
    public String macroSave(HttpServletRequest req) {
        String[] check1 = req.getParameterValues("check1");
        String[] check2 = req.getParameterValues("check2");
        String[] check3 = req.getParameterValues("check3");
        String[] check4 = req.getParameterValues("check4");
        String[] check5 = req.getParameterValues("check5");
        String[] check6 = req.getParameterValues("check6");
        String[] check7 = req.getParameterValues("check7");
        String[] check8 = req.getParameterValues("check8");
        String[] check9 = req.getParameterValues("check9");
        String[] check10 = req.getParameterValues("check10");
        String table1 = req.getParameter("check1name");
        String table2 = req.getParameter("check2name");
        String table3 = req.getParameter("check3name");
        String table4 = req.getParameter("check4name");
        String table5 = req.getParameter("check5name");
        ArrayList<String> tableName = new ArrayList<>();
        tableName.add(table1);
        tableName.add(table2);
        tableName.add(table3);
        tableName.add(table4);
        tableName.add(table5);
        ArrayList<String> selCol;
        ArrayList<ArrayList<String>> sel = new ArrayList<>();
        ArrayList<String> stndCol;
        ArrayList<ArrayList<String>> stnd = new ArrayList<>();

        if (check1 != null) {
            selCol = new ArrayList<>(Arrays.asList(check1));
            sel.add(selCol);
        }
        if (check2 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check2));
            stnd.add(stndCol);
        }
        if (check3 != null) {
            selCol = new ArrayList<>(Arrays.asList(check3));
            sel.add(selCol);
        }
        if (check4 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check4));
            stnd.add(stndCol);
        }
        if (check5 != null) {
            selCol = new ArrayList<>(Arrays.asList(check5));
            sel.add(selCol);
        }
        if (check6 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check6));
            stnd.add(stndCol);
        }
        if (check7 != null) {
            selCol = new ArrayList<>(Arrays.asList(check7));
            sel.add(selCol);
        }
        if (check8 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check8));
            stnd.add(stndCol);
        }
        if (check9 != null) {
            selCol = new ArrayList<>(Arrays.asList(check9));
            sel.add(selCol);
        }
        if (check10 != null) {
            stndCol = new ArrayList<>(Arrays.asList(check10));
            stnd.add(stndCol);
        }

        System.out.println("table_name : "+tableName);
        System.out.println("sel_cols : "+sel);
        System.out.println("stnd_cols : "+stnd);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8082/macroprocess";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table_names", tableName);
        jsonObject.put("sel_cols", sel);
        jsonObject.put("stnd_cols", stnd);
        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);


        return "redirect:/";
    }

//    @GetMapping("macro/delete")
//    public String macrodel(HttpServletRequest req) {
//        String macroName = req.getParameter("macro");
//        macro = new Macro(macroName);
//        eService.deleteMacro(macro);
//        return "redirect:/";
//    }
//
//    @PostMapping("macro/excute")
//    public String macroshow(HttpServletRequest req) {
//        String macroName = req.getParameter("macroName");
//        Map<String, String> map = new HashMap<>();
//        map.put("macroName", "\'" + macroName + "\'");
//        macro = eService.getMacro(map);
//        System.out.println(macro);
//
//        return "redirect:/";
//    }

    @GetMapping("table/list")
    public String getTable(@RequestParam(defaultValue = "1") int pageNo, @RequestParam String tableName, @RequestParam(defaultValue = "10") int rowsPer, Model model) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        int totalRows = eService.getTotalRows(map);

        Pager pager = new Pager(rowsPer, 5, totalRows, pageNo, tableName);

        List<Map<String, String>> list = eService.getTableList(pager);
        model.addAttribute("list", list);
        model.addAttribute("pager", pager);
        return "getDataTable";
    }

}
