package com.example.demo.hobby.domain.entity;


import com.example.demo.member.domain.entity.Member;
import com.example.demo.store.MemberHobby;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter @AllArgsConstructor
@NoArgsConstructor @Builder
@Setter
@Table(name = "hobbies")
public class Hobby {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "hobby")
    private List<MemberHobby> members;
}
