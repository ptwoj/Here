package com.example.demo.members.domain.request;

//진짜 로그인을 하려며면 이메일 하고, 패스워드가 필요하잖아? 그래서 만든건데,
//record 클래스를 사용하면 데이터를 저장하고 조회하는 데에 사용되는
// 간단한 데이터 클래스를 간결하게 작성하는데 도움을 주는 기능
/*
레코드 타입은 불변(immutable)하며, 클래스를 정의할 때 자동으로
 필드, 생성자, equals(), hashCode(), toString() 메서드 등을 생성
 */
public record LoginRequest(String email, String password) {
}
