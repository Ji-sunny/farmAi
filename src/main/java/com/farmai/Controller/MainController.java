package com.farmai.Controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @RequestMapping("/")
    public static String index(){
        return "index";
    }

    @RequestMapping("modeling1")
    public static String modeling1(){
        return "modeling1";
    }
    @RequestMapping("modeling2")
    public static String modeling2(){
        return "modeling2";
    }




    @GetMapping("modeling/bar")
    public String showBar(Model model) {

        return "bartest";
    }

    @GetMapping("modeling/table")
    public String showshow (){
        return "tabletest";
    }

    @PostMapping("modeling/table")
    public String showTable(HttpServletResponse response)throws Exception {
        JSONObject jsonobj = new JSONObject();
        response.setContentType("application/json; charset = UTF-8");
        PrintWriter pw = response.getWriter();
        jsonobj.put("rec", 0.5);
        jsonobj.put("accuracy", 0.05);

        String json = jsonobj.toString();
        pw.println(json);
        pw.flush();
        pw.close();
//        model.addAttribute("test", "Hello");
        return "tabletest";
    }
}
