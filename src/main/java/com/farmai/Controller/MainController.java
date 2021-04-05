package com.farmai.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @RequestMapping(value = "/")
    public static String index(){
        logger.info("실행 : main");
        return "index";
    }
    @RequestMapping(value = "modeling")
    public static String modeling(){
        logger.info("실행 : modeling");
        return "modeling";
    }

}
