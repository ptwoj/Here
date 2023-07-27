package com.example.demo.member.controller;

import com.example.demo.member.domain.request.MemberRequest;
import com.example.demo.member.domain.response.MemberResponse;
import com.example.demo.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService service;
    @GetMapping
    public Page<MemberResponse> getAll(
            @RequestParam(name = "name",
                    required = false,
                    defaultValue = "") String name,
            @RequestParam(name = "size",
            required = false,
            defaultValue = "10") Integer size,
            @RequestParam(name = "page",
                    required = false,
                    defaultValue = "0") Integer page
    ){
       PageRequest request = PageRequest.of(page, size);
       return service.findAll(name, request);
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
