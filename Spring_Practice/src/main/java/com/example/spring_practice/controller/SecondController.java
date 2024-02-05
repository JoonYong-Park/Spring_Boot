package com.example.spring_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

    @GetMapping("/fruit")
    public String qwer(Model model){
        String[] arr = {"Apple", "Banana", "Cherry", "Dingle very", "Egg fruit"};
        int randomNum = (int) (Math.random() * arr.length);

        model.addAttribute("fruit", arr[randomNum]);
        System.out.println(arr[randomNum]);
        return "fruit";
    }

    @GetMapping("/number")
    public String randomNumber(Model model){
        int randomNum = (int) (Math.random() * 100);

        model.addAttribute("number", randomNum);
        return "number";
    }


}
