package com.example.demo1.member.domain.request;
import com.example.demo1.member.domain.entity.Member;

public record MemberRequest(String name, Integer age) {
    public Member toEntity(){
        return Member
                .builder()
                .age(age)
                .name(name)
                .build();
    }
}