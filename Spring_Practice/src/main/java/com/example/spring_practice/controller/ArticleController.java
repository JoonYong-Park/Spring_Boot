package com.example.spring_practice.controller;

import com.example.spring_practice.dto.ArticleForm;
import com.example.spring_practice.entity.Article;
import com.example.spring_practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new") // 폼 페이지 보여주기
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")  // 폼 데이터 받기
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());   // 잘 담겼는지 확인

        // 1. DTO -> (Controller) -> Entity
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. Entity -> (repagitory) -> DB
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());

        // 3. return view page
        return "";
    }

}
