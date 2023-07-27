package com.example.demo.todos.domain.dto;

import lombok.*;

//Todo를 조회할 때 다양한 검색 조건을 표현하고 전달하기 위해 만들어진 클래스
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class TodoCondition {
    private String title;
    private String content;
    private Boolean isDone;
    private Integer likeGoe;
    private Integer likeLoe;
}