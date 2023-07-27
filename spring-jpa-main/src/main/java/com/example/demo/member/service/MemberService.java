package com.example.demo.member.service;

import com.example.demo.member.domain.entity.Member;
import com.example.demo.member.domain.request.MemberRequest;
import com.example.demo.member.domain.response.MemberResponse;
import com.example.demo.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {
    private final EntityManager em;
    private final MemberRepository memberRepository;

    public void delete(Long id){
//        Member member = em.find(Member.class, id);
//        em.remove(member);
        memberRepository.deleteById(id);
    }

    public MemberResponse findById(Long id){
//        Member member = em.find(Member.class, id);
        Optional<Member> byId = memberRepository.findById(id);
        Member member = byId.orElseThrow(() ->
                new RuntimeException("Not Found Member bY"+id));
        return new MemberResponse(member);
    }

    public Page<MemberResponse> findAll(String name, PageRequest request){
        Page<Member> all = memberRepository
                .findAllByNameContaining(name, request);
        return all.map(MemberResponse::new);
//        return em
//                .createQuery(
//                        "select m from Member m " +
//                        "left join fetch m.hobbies h " +
//                                "join fetch h.hobby " +
//                                "where m.name like :name"
//                        , Member.class)
//                .setParameter("name", "%"+name+"%")
//                .getResultList()
//                .stream()
//                .map(MemberResponse::new)
//                .toList();
    }

    public void insert(MemberRequest request){
//        em.persist(request.toEntity());
        Member save = memberRepository.save(request.toEntity());
        log.info("SAVED_ID : {}", "SAVED_NAME : {}", save.getId().toString());
    }


    public MemberResponse update(Long id, MemberRequest request){
//        Member member = em.find(Member.class, id);
//        member.setAge(request.age());
//        member.setName(request.name());

//        Member member = memberRepository.findById(id)
//                .orElseThrow(() ->
//                new RuntimeException("Not Found Member bY"+id));
//        member.setAge(request.age());
//        member.setName(request.name());
       Optional<Member> byId = memberRepository.findById(id);
       if(!byId.isEmpty()) throw new RuntimeException("없는 유저 그냥 수정");
        Member member = new Member(id, request.name(), request.age(), null);
        Member save =  memberRepository.save(member);
        return new MemberResponse(save);
    }
}
