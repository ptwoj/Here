package com.example.demo.hobby.domain.request;


import com.example.demo.hobby.domain.entity.Hobby;

public record HobbyRequest(String name) {
    public Hobby toEntity(){
        return Hobby
                .builder()
                .name(name)
                .build();
    }
}
