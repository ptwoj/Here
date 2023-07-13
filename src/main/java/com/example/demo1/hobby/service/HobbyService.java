package com.example.demo1.hobby.service;
import com.example.demo1.hobby.domain.entity.Hobby;
import com.example.demo1.hobby.domain.request.HobbyRequest;
import com.example.demo1.hobby.domain.response.HobbyResponse;
import com.example.demo1.member.domain.entity.Member;
import com.example.demo1.member.domain.request.MemberRequest;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//    @Transactional : 트랜잭션관리와 롤백기능이 있다.
@Service
@RequiredArgsConstructor
@Transactional
public class HobbyService {
    private final EntityManager em;


    public void save(HobbyRequest request){
        Hobby hobby = new Hobby(null, request.getName(),
                new Member(request.getMemberID(),null,null,null));
        em.persist(hobby);
        System.out.println(hobby.getId());
    }

    public HobbyResponse findById(Long id){
        Hobby hobby = em.find(Hobby.class, id);
        return new HobbyResponse(hobby);
    }

    public List<HobbyResponse> findAll(){
//        mysql, oracle, mssql ...
        return em
                .createQuery("select h from Hobby h", Hobby.class)
                .getResultStream()
                .map(HobbyResponse::new)
                .toList();
    }

    public void delete(Long id){
        Hobby hobby = em.find(Hobby.class, id);
        em.remove(hobby);
    }
}