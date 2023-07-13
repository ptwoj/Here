package com.example.demo1.member.service;


import com.example.demo1.member.domain.entity.Member;
import com.example.demo1.member.domain.request.MemberRequest;
import com.example.demo1.member.domain.response.MemberResponse;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final EntityManager em;

    public void delete(Long id){
        Member member = em.find(Member.class, id);
        em.remove(member);
    }

    public MemberResponse findById(Long id){
        Member member = em.find(Member.class, id);
        return new MemberResponse(member);
    }

    public List<MemberResponse> findAll(){
        return em
                .createQuery("select m from Member m " +
                        "left join fetch m.hobbies "
                        , Member.class)
                .getResultList()
                .stream()
                .map(MemberResponse::new)
                .toList();
    }

    public void insert(MemberRequest request){
        em.persist(request.toEntity());
    }

    public MemberResponse update(Long id, MemberRequest request){
        Member member = em.find(Member.class, id);
        member.setAge(request.age());
        member.setName(request.name());
        return MemberResponse.from(member);
    }
}