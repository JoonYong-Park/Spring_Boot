package com.example.spring_practice.dto;

import com.example.spring_practice.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// 폼 데이터를 받아 올 그릇, 즉 DTO(Data Transfer Object)를 만들어 줍니다.

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;         // 아이디 받을 필드
    private String title;    // 제목 받을 필드
    private String content;  // 내용 받을 필드

//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    // DTO -> Entity
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
