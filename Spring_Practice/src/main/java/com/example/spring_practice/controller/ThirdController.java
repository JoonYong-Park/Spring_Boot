package com.example.spring_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThirdController {

    @GetMapping("/lunch")
    public String lunch(Model model){
        String[] arr = {"중식", "한식",  "일식", "양식", "분식"};
        int randomNum = (int) (Math.random() * arr.length);

        model.addAttribute("menu", arr[randomNum]);
        return "lunch";
    }

    @GetMapping("/dinner")
    public String diner(Model model){
        String[] arr = {"집밥", "편의점", "햄버거", "치킨", "피자"};
        int randomnum = (int) (Math.random() * arr.length);

        model.addAttribute("menu", arr[randomnum]);
        return "dinner";
    }
}
