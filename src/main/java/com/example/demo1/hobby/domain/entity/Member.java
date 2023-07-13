package com.example.demo1.hobby.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor

// 빌더 패턴을 사용하여 객체를 생성할 수 있도록 해준다. 빌더 패턴은 인스턴스 생성 시 가독성과 유연성을 높여주는 역할
@Builder
@Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    private Integer age;
}
