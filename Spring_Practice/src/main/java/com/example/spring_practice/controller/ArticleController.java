package com.example.spring_practice.controller;

import com.example.spring_practice.dto.ArticleForm;
import com.example.spring_practice.entity.Article;
import com.example.spring_practice.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    @Autowired
    private ArticleRepository articleRepository;

    // 폼 페이지 보여주기
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    // 폼 데이터 받기
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());   // 잘 담겼는지 확인
        log.info(form.toString());

        // 1. DTO -> (Controller) -> Entity
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Entity -> (repository) -> DB
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        // 3. return view page
        return "";
    }

    // Read
    @GetMapping("/articles/{id}") // {id} : Path Variable
    public String show(@PathVariable Long id, Model model){
        log.info("id : " + id);

        // 1. DB에서 id에 맞는 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);  // 만약 해당 아이디값이 없다면 null을 반환

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 설정
        return "articles/show";
    }

}