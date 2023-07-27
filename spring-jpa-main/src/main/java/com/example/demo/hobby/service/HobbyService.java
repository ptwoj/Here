package com.example.demo.hobby.service;

import com.example.demo.hobby.domain.entity.Hobby;
import com.example.demo.hobby.domain.repository.HobbyRepository;
import com.example.demo.hobby.domain.request.ConnectRequest;
import com.example.demo.hobby.domain.request.HobbyRequest;
import com.example.demo.hobby.domain.response.HobbyResponse;
import com.example.demo.member.domain.entity.Member;
import com.example.demo.member.domain.request.MemberRequest;
import com.example.demo.store.MemberHobby;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HobbyService {
    private final EntityManager em;
    private final HobbyRepository hobbyRepository;
    public void connect(ConnectRequest request){
        MemberHobby memberHobby = new MemberHobby(null,
                Member
                .builder()
                .id(request.getMemberId())
                .build(),
                Hobby
                .builder()
                .id(request.getHobbyId())
                .build());
        em.persist(memberHobby);
    }


    public void save(HobbyRequest request) {
        Hobby save = hobbyRepository.save(request.toEntity());
        log.info("SAVED_ID : {}", "SAVED_NAME : {}", save.getId().toString());
    }
//        Hobby hobby = new Hobby(null,
//                request.getName(),
//                null);
//        em.persist(hobby);
//        System.out.println(hobby.getId());


    public HobbyResponse findById(Long id){
//        Hobby hobby = em.find(Hobby.class, id);
        Optional<Hobby> byId = hobbyRepository.findById(id);
        Hobby hobby = byId.orElseThrow(() ->
                new RuntimeException("Not Found Member bY"+id));
        return new HobbyResponse(hobby);
    }

    public Page<HobbyResponse> findAll(String name, PageRequest request) {
        Page<Hobby> all = hobbyRepository
                .findAllByNameContaining(name, request);
        return all.map(HobbyResponse::new);

    }

//        mysql, oracle, mssql ...
//        return em
//                .createQuery("select h from Hobby h " +
//                        "left join fetch h.members m " +
//                                "join fetch m.member"
//                        , Hobby.class)
//                .getResultStream()
//                .map(HobbyResponse::new)
//                .toList();
//    }

    public void delete(Long id){
//        Hobby hobby = em.find(Hobby.class, id);
//        em.remove(hobby);
        hobbyRepository.deleteById(id);
    }
}
