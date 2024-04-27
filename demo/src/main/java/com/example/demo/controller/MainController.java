package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("data", "Hello <b>Thymeleaf</b>!");
        return "index";
    }

    @GetMapping("/thymeleaf")
    public String moveThymeleaf(
            @RequestParam(value="no", required=false, defaultValue="1") int usernum,
            Model model){

        model.addAttribute("userNum", usernum);

        model.addAttribute("data", "<b>Thymeleaf Main</b>!");
        return "thymeleaf/main";
    }

    @GetMapping("/view")
    public String moveView(){
        return "view";
    }
}
