package com.example.demo1.member.controller;

import com.example.demo1.member.domain.response.MemberResponse;
import com.example.demo1.member.domain.request.MemberRequest;
import com.example.demo1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService service;
    @GetMapping
        public List<MemberResponse> getAll(){
        return service.findAll();
    }
    @GetMapping("{id}")
    public MemberResponse getById(@PathVariable("id") Long id){
        return service.findById(id);
    }
    @PutMapping("{id}")
    public MemberResponse updateById(
            @PathVariable("id") Long id
            , @RequestBody MemberRequest request){
        return service.update(id, request);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id){
        service.delete(id);
    }
    @PostMapping
    public void insert(@RequestBody MemberRequest request){
        service.insert(request);
    }
}

