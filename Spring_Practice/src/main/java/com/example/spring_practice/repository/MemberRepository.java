package com.example.spring_practice.repository;

import com.example.spring_practice.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface MemberRepository extends CrudRepository<Member, Long> {

    @Override
    ArrayList<Member> findAll(); // 기본적으로 제공되는 findAll() 메소드를 오버라이딩해서 사용합니다.


}
