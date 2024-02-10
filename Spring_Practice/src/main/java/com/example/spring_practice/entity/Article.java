package com.example.spring_practice.entity;

import jakarta.persistence.*;

@Entity
public class Article {

    @Id // 대표값 지정
    @GeneratedValue // 1, 2, 3, ... 자동 생성 어노테이션!!
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // 생성자 추가
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // 기본 생성자 추가
    public Article() {

    }

    // toString() 추가
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getId() {
        return id.toString();
    }
}
