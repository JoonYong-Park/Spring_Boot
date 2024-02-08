package com.example.spring_practice.controller;

import com.example.spring_practice.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")  // 폼데이터 받기
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());   // 잘 담겼는지 확인


        return "";
    }

}
