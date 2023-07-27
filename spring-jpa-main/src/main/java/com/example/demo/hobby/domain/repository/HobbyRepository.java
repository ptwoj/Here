package com.example.demo.hobby.domain.repository;

import com.example.demo.hobby.domain.entity.Hobby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface HobbyRepository
        extends JpaRepository<Hobby, Long> {
    Page<Hobby> findAllByNameContaining(String name, Pageable pageable);

    @Query("select h from Hobby h " +
            "left join fetch h.members m " +
            "join fetch m.member")
    List<Hobby> findAllFetchByNameContaining(@Param("hobby") String name);


}
