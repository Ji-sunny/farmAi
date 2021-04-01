package com.farmai.Controller;

import com.farmai.DTO.Macro;
import com.farmai.DTO.Pager;
import com.farmai.Service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("modeling")
    public String macroSave(HttpServletRequest req) {
        String one = req.getParameter("macro1");
        String two = req.getParameter("macro2");
        String three = req.getParameter("macro3");
        String four = req.getParameter("macro4");
        String five = req.getParameter("macro5");
        String name = req.getParameter("macroName");

        System.out.println(one + two + three + four + five + name);

//        macro = new Macro(name, one, two, three, four, five);
//        int tmp = eService.insertMacro(macro);

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
    @GetMapping("table/list")
    public String getTable(@RequestParam(defaultValue = "1") int pageNo, @RequestParam String tableName, @RequestParam (defaultValue = "10")int rowsPer, Model model) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        int totalRows = eService.getTotalRows(map);

        Pager pager = new Pager(rowsPer, 5, totalRows, pageNo, tableName);

        List<Map<String,String>> list = eService.getTableList(pager);
        model.addAttribute("list", list);
        model.addAttribute("pager",pager);
        return "getDataTable";
    }



}
