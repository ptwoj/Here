package com.example.demo1.hobby.domain.response;

import com.example.demo1.hobby.domain.entity.Hobby;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class HobbyResponse {
    private Long id;
    private String name;
    public HobbyResponse(Hobby hobby){
        this.id = hobby.getId();
        this.name = hobby.getName();
    }
}