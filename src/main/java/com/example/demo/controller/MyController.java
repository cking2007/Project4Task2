package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * @version V1.0
 * @description
 * @date 2022/4/7 5:35 PM
 **/

@Controller
public class MyController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
