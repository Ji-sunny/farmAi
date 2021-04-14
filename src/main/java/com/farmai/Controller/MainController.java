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

    @RequestMapping("modeling")
    public static String modeling(){
        return "modeling";
    }
    @RequestMapping("visualization")
    public static String visualization(){
        return "visualization";
    }

}
