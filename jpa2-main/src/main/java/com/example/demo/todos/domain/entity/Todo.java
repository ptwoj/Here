package com.example.demo.todos.domain.entity;


import com.example.demo.members.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // JPA 엔티티로 지정하는 어노테이션
@Table(name = "todos") // 해당 엔티티와 매핑할 테이블 이름 지정
@AllArgsConstructor // 모든 필드를 사용하는 생성자를 자동으로 생성하는 어노테이션
@NoArgsConstructor // 기본 생성자를 자동으로 생성하는 어노테이션
@Getter // 모든 필드에 대한 getter 메서드를 자동으로 생성하는 어노테이션
@Builder // 빌더 패턴을 사용하여 객체를 생성하는 빌더 메서드를 자동으로 생성하는 어노테이션
public class Todo {

    @Id // 기본 키를 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 지정하는 어노테이션
    private Long id; // Todo 엔티티의 기본 키 필드

    private String title; // Todo의 제목을 저장하는 필드
    private String content; // Todo의 내용을 저장하는 필드
    private boolean isDone; // Todo의 완료 여부를 저장하는 필드
    private Integer likeCount; // Todo의 좋아요 개수를 저장하는 필드

    @ManyToOne(fetch = FetchType.LAZY) // 다대일(N:1) 관계를 나타내는 어노테이션
    private Member member; // Todo와 연관된 Member 엔티티를 저장하는 필드

    // isDone 필드를 변경하여 Todo의 완료 상태를 토글하는 메서드
    // 토글이란 ex) 불이 켜져있으면 불 스위치를 누르면 끄는 스위치가 되고, 꺼져있으면 켜진다.
    // 이러한 원리로 완료되었다가 되지 않았다가 이런거다
    public void changeIsDone() {
        isDone = !isDone;
    }
}