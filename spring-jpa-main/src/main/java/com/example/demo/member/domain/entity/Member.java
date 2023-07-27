package com.example.demo.member.domain.entity;

import com.example.demo.store.MemberHobby;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Builder @Setter
@Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberHobby> hobbies;
}
