package com.example.demo1.hobby.domain.request;

import com.example.demo1.hobby.domain.entity.Hobby;
import com.example.demo1.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HobbyRequest {
    private String name;
    private Long memberID;

}