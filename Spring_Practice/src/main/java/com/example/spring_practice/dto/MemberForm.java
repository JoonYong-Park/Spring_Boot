package com.example.spring_practice.dto;

import com.example.spring_practice.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    // DTO -> Entity
    public Member toEntity() {
        return new Member(id, email, password);
    }
}
