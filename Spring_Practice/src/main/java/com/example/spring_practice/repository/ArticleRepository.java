package com.example.spring_practice.repository;

import com.example.spring_practice.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> { //CrudRepository를 상속받아서 사용합니다.
    // 기본적인 CRUD 메소드가 자동으로 생성됩니다.
    // save, findById, findAll, delete, deleteById, count, exists 등등
    // 추가적인 메소드를 만들어서 사용할 수 있습니다.
    // findByTitle, findByContent, findByTitleAndContent 등등
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details

    @Override
    ArrayList<Article> findAll(); // 기본적으로 제공되는 findAll() 메소드를 오버라이딩해서 사용합니다.

}
