package com.example.demo1.hobby.service;

import com.example.demo1.hobby.domain.entity.Hobby;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyService {
    private final EntityManager em;

    @Transactional
    public void save(String name){
        em.persist(new Hobby(null, name));
    }
    @Transactional
    public Hobby findByID(Long id){
        Hobby hobby = em.find(Hobby.class, id);
        return hobby;
    }
}
