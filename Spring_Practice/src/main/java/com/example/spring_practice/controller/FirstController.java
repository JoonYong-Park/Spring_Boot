package com.example.spring_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String hello(Model model) {
        model.addAttribute("username","준용");
        return "hello";
    }

    @GetMapping("/bye")
    public String goodbye(Model model){
        model.addAttribute("username","준용");
        return "goodbye";
    }
}
