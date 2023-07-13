package com.example.demo1.member.domain.entity;

import com.example.demo1.hobby.domain.entity.Hobby;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    
//    원래스는 리스트를 쓰면 받지를 못한다. 왜? 리스트인데, 한 테이블에 농구,야구.축구...이렇게 들어가니까
//    근데 OneTOMany가 그거를 해준다
    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Hobby> hobbies;
}
