package com.example.demo1.hobby.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SaveMemberDto {
    private String  name;
    private Integer age;

}


