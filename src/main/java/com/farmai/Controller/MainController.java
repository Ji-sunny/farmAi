package com.farmai.Controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


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
    public String showBar(Model model) throws Exception {
        BufferedReader in = null;
        URL obj = new URL("http://127.0.0.1:8082/test"); // 호출할 url
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String str = null;
        StringBuffer buff = new StringBuffer();
        while ((str = in.readLine()) != null) {
            buff.append(str + "\n");
        }
        String data = buff.toString().trim();

        System.out.println("data : " + data);

        JSONParser jsonParser = new JSONParser();
        org.json.simple.JSONObject json = (org.json.simple.JSONObject) jsonParser.parse(data);

        if (!json.get("result").equals("success") || data.equals("")) {
            throw new RuntimeException((String) json.get("result"));
        }
        /////////////////////////////////////////////

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");

        String json2 = "{\n" +
                "    \"box_num\" : \"10\",\n" +
                "    \"predict\" : 0.9999998202\n" +
                "}, {\n" +
                "    \"box_num\" : \"11\",\n" +
                "    \"predict\" : 0.0000001159\n" +
                "}, {\n" +
                "    \"box_num\" : \"14\",\n" +
                "    \"predict\" : 0.0000000639\n" +
                "}";
//        System.out.println("bar table");
        model.addAttribute("json", json2);
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
        jsonobj.put("precision", 0.8);
        jsonobj.put("specificity", 0.85);
        String json = jsonobj.toString();
        pw.println(json);
        pw.flush();
        pw.close();
        return "tabletest";
    }
}
