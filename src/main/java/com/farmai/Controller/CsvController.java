package com.farmai.Controller;

import com.farmai.DTO.FileStorage;
import com.farmai.DTO.Macro;
import com.farmai.DTO.Pager;
import com.farmai.Service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class CsvController {

    private static final Logger logger = LoggerFactory.getLogger(CsvController.class);

    @Autowired
    private CsvService eService;

    @Resource(name = "uploadPath")
    String uploadPath;

    private Macro macro;


    @GetMapping("delete")
    public String delete(HttpServletRequest req) {
        String filename = req.getParameter("csvexcel");
//        System.out.println(filename);
        String location = uploadPath + filename+".csv";
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
        Map<String, String>map = new HashMap<>();
        map.put("filename","\'%"+filename.toUpperCase()+"%\'");
        System.out.println(filename);
        eService.deleteCsvData(map);

        return "redirect:/";
    }

    @PostMapping("macro/save")
    public String macroSave(HttpServletRequest req) {
        String one = req.getParameter("macro1");
        String two = req.getParameter("macro2");
        String three = req.getParameter("macro3");
        String four = req.getParameter("macro4");
        String five = req.getParameter("macro5");
        String name = req.getParameter("macroName");

        System.out.println(one + two + three + four + five + name);

        macro = new Macro(name, one, two, three, four, five);
        int tmp = eService.insertMacro(macro);

        return "redirect:/";
    }

    @GetMapping("macro/delete")
    public String macrodel(HttpServletRequest req) {
        String macroName = req.getParameter("macro");
        macro = new Macro(macroName);
        eService.deleteMacro(macro);
        return "redirect:/";
    }

    @PostMapping("macro/excute")
    public String macroshow (HttpServletRequest req) {
        String macroName = req.getParameter("macroName");
        Map<String, String>map = new HashMap<>();
        map.put("macroName","\'"+macroName+"\'");
        macro = eService.getMacro(map);
        System.out.println(macro);

        return "redirect:/";
    }
    @GetMapping("table/list1")
    public String tablemake(@RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam String tableName,
                            @RequestParam (defaultValue = "10")int rowsPer, Model model) {
//        System.out.println(pageNo);
//        System.out.println(tableName);
//        System.out.println(rowsPer);
        Map<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        int totalRows = eService.getTotalRows(map);
//        System.out.println(totalRows);
        Pager pager = new Pager(rowsPer, 5, totalRows, pageNo, tableName);

//        System.out.println("pager : " + pager);

        List<Map<String,String>> list = eService.getTableList(pager);
//        System.out.println(list);
        model.addAttribute("list", list);
        model.addAttribute("pager",pager);
        return "/tabletest";
    }

    @GetMapping("table/list2")
    public String tablemake2(@RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam String tableName,
                            @RequestParam (defaultValue = "10")int rowsPer, Model model) {
//        System.out.println(pageNo);
//        System.out.println(tableName);
//        System.out.println(rowsPer);
        Map<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        int totalRows = eService.getTotalRows(map);
//        System.out.println(totalRows);
        Pager pager = new Pager(rowsPer, 5, totalRows, pageNo, tableName);

//        System.out.println("pager : " + pager);

        List<Map<String,String>> list = eService.getTableList(pager);
//        System.out.println(list);
        model.addAttribute("list", list);
        model.addAttribute("pager",pager);
        return "/tabletest";
    }




}
