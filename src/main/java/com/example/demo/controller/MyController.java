package com.example.demo.controller;

import com.example.demo.DashEntity;
import com.example.demo.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author
 * @version V1.0
 * @description
 * @date 2022/4/7 5:35 PM
 **/

@Controller
public class MyController {

    @Autowired
    public MyService myService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model) {

        long totalCount = myService.getTotalCount();

        if (totalCount == 0) {
            return "index";
        }
        int totalSearchCount = myService.getTotalSearchCount();
        int mostTime = myService.getMostTime();

        int countByCountry = myService.getCountBySearchCountry();
        String country = myService.getCountry();
        int leastTime = myService.getLeastTime();

        List<DashEntity> listDash = myService.getDashData();

        model.addAttribute("totalSearchCount", totalSearchCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("mostTime", mostTime);

        model.addAttribute("countByCountry", countByCountry);
        model.addAttribute("country", country);
        model.addAttribute("leastTime", leastTime);
        model.addAttribute("listDash", listDash);
        return "dashboard";
    }
}
