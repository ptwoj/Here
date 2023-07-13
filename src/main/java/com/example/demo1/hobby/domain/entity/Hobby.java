package com.example.demo1.hobby.domain.entity;

import com.example.demo1.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;
//Entity은 테이블를 위한 설계?
@Entity
/*
@Entity 데이터베이스의 테이블과 클래스의 속성들을 연결해 줄 수 있는 ORM(Object-Relational Mapping) 프레임워크에서 주로 사용
ORM은 객체와 관계형 데이터베이스 간의 매핑을 자동화해주는 기술이나 프레임워크를 의미
SQL 쿼리를 직접 작성하지 않고도 객체를 데이터베이스에 저장하고 조회할 수 있는 방법을 제공
개발자는 데이터베이스에 직접 접근하는 대신 객체를 다루는 방식으로 개발을 할 수 있다
*/
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
//@Getter: 모든 필드에 대한 getter 메서드를 자동으로 생성
//@Setter: 이 주석은 모든 필드에 대한 setter 메서드를 자동으로 생성
//@AllArgsConstructor: 모든 필드를 포함한 생성자를 자동으로 생성
//@NoArgsConstructor: 이 주석은 파라미터가 없는 기본 생성자를 자동으로 생성
//@Builder: 이 주석은 빌더 패턴을 사용하여 객체를 생성할 수 있도록 해줌. 빌더 패턴은 인스턴스 생성 시 가독성과 유연성을 높여주는 역할
@Table(name = "hobbies")
//@Table(name = "hobbies"): 이 주석은 해당 클래스와 매핑되는 데이터베이스 테이블의 이름을 지정 "hobbies"라는 이름의 테이블과 연결된다.
//UUID는 오토로 쫘라랅 해주잖아? 그거를 차량번호처럼 고유 번호가 있게 만드는거
public class Hobby {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Member member;
}

