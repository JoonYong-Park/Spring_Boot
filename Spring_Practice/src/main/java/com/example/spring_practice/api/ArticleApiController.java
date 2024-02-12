package com.example.spring_practice.api;

import com.example.spring_practice.dto.ArticleForm;
import com.example.spring_practice.entity.Article;
import com.example.spring_practice.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI용 컨트롤러! JSON으로 데이터를 반환하는 컨트롤러
public class ArticleApiController {

    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id)  {
        return articleRepository.findById(id).orElse(null);
    }

    // POST : 데이터를 받아서 저장
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) { // @RequestBody : JSON으로 받은 데이터를 객체로 변환
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }


    // PATCH : 데이터를 받아서 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기(수정용 엔티티 생성)
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기(대상이 없거나 id가 다른 경우)
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
