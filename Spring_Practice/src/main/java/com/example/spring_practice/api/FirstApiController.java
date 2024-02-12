package com.example.spring_practice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI용 컨트롤러! JSON으로 데이터를 반환하는 컨트롤러
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "안녕하세요. 반갑습니다.";
    }

}
