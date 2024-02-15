package com.example.spring_practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Member {

    @Id // 키값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1, 2, 3, ... 자동 생성 어노테이션/ (DB가 id를 자동생성)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;
}
