package com.example.demo.hobby.controller;

import com.example.demo.hobby.domain.entity.Hobby;
import com.example.demo.hobby.domain.request.ConnectRequest;
import com.example.demo.hobby.domain.request.HobbyRequest;
import com.example.demo.hobby.domain.response.HobbyResponse;
import com.example.demo.hobby.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hobbies")
public class HobbyController {
    private final HobbyService service;
    @PostMapping
    public void save(@RequestBody HobbyRequest request){
        service.save(request);
    }

    @GetMapping("{id}")
    public HobbyResponse getById(@PathVariable("id") Long id){
        return service.findById(id);
    }
    @GetMapping
    public Page<HobbyResponse> getAll(
        @RequestParam(name = "name",
                required = false,
                defaultValue = "") String name,
        @RequestParam(name = "size",
                required = false,
                defaultValue = "10") Integer size,
        @RequestParam(name = "page",
                required = false,
                defaultValue = "0 ") Integer page
    ){
        PageRequest request = PageRequest.of(page, size);
        return service.findAll(name, request);
}


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    @PostMapping("connect")
    public void connect(@RequestBody ConnectRequest request){
        service.connect(request);
    }

}
