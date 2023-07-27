package com.example.demo.store;

import com.example.demo.hobby.domain.entity.Hobby;
import com.example.demo.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Table(name = "member_hobby")
public class MemberHobby {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Hobby hobby;
}
