package com.example.spring_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

    @GetMapping("/random")
    public String qwer(Model model){
        String[] arr = {"Apple", "Banana", "Cherry", "Dingle Very", " Eggfruit"};

        int randomNum = (int) (Math.random() * arr.length);

        model.addAttribute("random", arr[randomNum]);
        return "random";
    }


}
