package com.example.demo1.hobby.controller;

import com.example.demo1.hobby.domain.entity.Hobby;
import com.example.demo1.hobby.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hobbies")
public class HobbyController {
    private final HobbyService service;
    @PostMapping
    public void save(@RequestBody Map<String, String> map){
        service.save(map.get("name"));
    }

    //get을 통해서 id를 보이는 역할이다
    //똑같이 @GetMapping을 통해서 일단 id를 주소창에 쓸 수 있게 해주고
    //@PathVariable("id") Long id을 통해서 id 값을 연결? 시키는?
    @GetMapping("{id}")
    public Hobby getById(@PathVariable("id") Long id) {
         return service.findByID(id);
    }
}
