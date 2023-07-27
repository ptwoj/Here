package com.example.demo.todos.domain.request;

import com.example.demo.members.domain.entity.Member;
import com.example.demo.todos.domain.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @AllArgsConstructor
public class TodoRequest {
    private String title;
    private String content;
    private Long memberId;

    // Setter 메서드
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    // Todo 엔티티로 변환하는 메서드
    public Todo toEntity(){
        // TodoRequest 객체의 memberId를 사용하여 Member 객체를 생성합니다.
        // Member 엔티티는 Todo 엔티티와 관계를 맺고 있으며, memberId를 통해 Todo와 Member 간의 연관관계를 설정합니다.
        Member member = Member.builder()
                .id(memberId) // Member의 id 필드를 TodoRequest 객체의 memberId로 설정합니다.
                .build();

        // Todo 엔티티를 생성하여 반환합니다.
        // Todo.builder()는 Todo 객체를 생성하기 위한 빌더 패턴의 시작점입니다.
        // Todo 엔티티의 필드 값들을 TodoRequest 객체의 필드 값들로 설정합니다.
        // Todo의 likeCount는 0으로 초기화되며, 위에서 생성한 Member 객체를 Todo의 member로 설정합니다.
        return Todo.builder()
                .content(content) // Todo의 content 필드를 TodoRequest 객체의 content로 설정합니다.
                .title(title) // Todo의 title 필드를 TodoRequest 객체의 title로 설정합니다.
                .member(member) // Todo의 member 필드를 위에서 생성한 Member 객체로 설정합니다.
                .likeCount(0) // Todo의 likeCount 필드를 0으로 설정합니다. (기본값)
                .build(); // 설정한 필드 값을 바탕으로 최종적으로 Todo 객체를 생성합니다.
        // 생성된 Todo 객체가 반환됩니다.
    }
}