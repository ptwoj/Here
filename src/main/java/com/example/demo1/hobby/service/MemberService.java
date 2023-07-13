package com.example.demo1.hobby.service;


import com.example.demo1.hobby.domain.request.SaveMemberDto;
import com.example.demo1.hobby.domain.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
//final이나 @NonNull으로 표시된 필드를 기반으로 한 생성자를 자동으로 생성
//@RequiredArgsConstructor를 사용하면 클래스의 필드를 초기화하는 생성자를 자동으로 생성, 이 생성자는 해당 필드를 인자로 받아 초기화하는 역할을 합니다.
@RequiredArgsConstructor
public class MemberService {

//EntityManager 타입의 em1 필드를 final로 선언하고, @RequiredArgsConstructor 어노테이션을 사용했기 때문에 해당 필드를 초기화하는 생성자가 자동으로 생성
//em1 필드를 통해 MemberService 클래스에서 JPA를 사용하여 데이터베이스와 상호작용하고, 회원 정보의 저장, 조회, 삭제, 업데이트 등의 기능을 수행
    private final EntityManager em1;

    // 회원 정보 저장 기능
    @Transactional
    public void save(SaveMemberDto memberDto) {
        // SaveMemberDto를 이용하여 Member 객체 생성
        Member member = Member.builder()
                .name(memberDto.getName())
                .age(memberDto.getAge())
                .build();
        // 왜 id를 빼냐? 그 이유는 아이디를 빼고 가져오려고 한다. 그러면 위에 같은 방법으로 하면 된다.
        // EntityManager를 이용하여 회원 정보를 데이터베이스에 저장
        em1.persist(member);
    }

    // ID에 해당하는 회원 정보 조회 기능
    @Transactional
    public Member findById(Long id) {
        // EntityManager를 이용하여 ID에 해당하는 회원 정보 조회
        Member member = em1.find(Member.class, id);
        return member;
    }

    // ID에 해당하는 회원 정보 삭제 기능
    @Transactional
    public void deleteById(Long id) {
        // EntityManager를 이용하여 ID에 해당하는 회원 정보 조회 및 삭제
        Member member = em1.find(Member.class, id);
        em1.remove(member);
    }

    // ID에 해당하는 회원 정보 업데이트 기능
    @Transactional
    public Member updateMember(Long id, SaveMemberDto saveMemberDto) {
        // EntityManager를 이용하여 ID에 해당하는 회원 정보 조회
        Member member = em1.find(Member.class, id);
        // SaveMemberDto의 정보로 회원 정보 업데이트
        member.setName(saveMemberDto.getName());
        member.setAge(saveMemberDto.getAge());
        return member;
    }
}