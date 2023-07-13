package com.example.demo1.member.domain.response;

import com.example.demo1.hobby.domain.entity.Hobby;
import com.example.demo1.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor @Getter
public class MemberResponse {
    private Long id;
    private String name;
    private Integer age;
    private List<HobbyDto> hobbies;

    public static MemberResponse from(Member member){
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getHobbies()
                        .stream()
                        .map(HobbyDto::from)
                        .toList()
        );
    }

    public MemberResponse(Member member){
        this.id = member.getId();
        this.age = member.getAge();
        this.name = member.getName();
        this.hobbies = member.getHobbies()
                .stream()
                .map(HobbyDto::new)
                .toList();
    }


    @Getter @AllArgsConstructor
    static class HobbyDto{
        private Long id;
        private String name;
        public HobbyDto(Hobby hobby){
            this.id = hobby.getId();
            this.name = hobby.getName();
        }
        public static HobbyDto from(Hobby hobby){
            return new HobbyDto(
                    hobby.getId(),
                    hobby.getName());
        }
    }


}