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

import java.util.List;

@Slf4j
@Controller
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
        return "redirect:/articles/" + saved.getId();
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

    // 인덱스
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 article을 가져오기
        List<Article> articleEntities = articleRepository.findAll();

        // 2. 가져온 article 묶음를 뷰로 전달
        model.addAttribute("articlesList", articleEntities);

        // 3. 보여줄 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO -> Entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. Entity -> Repository -> DB
        // 2-1. DB에 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2 기존 데이터가 있다면 수정
        if (target != null) {
            articleRepository.save(articleEntity);  // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트 하기
        return "redirect:/articles/" + articleEntity.getId();
    }
}