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

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
public class TableController {

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private CsvService eService;

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
