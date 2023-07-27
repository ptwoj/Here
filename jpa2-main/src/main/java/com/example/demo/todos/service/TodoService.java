package com.example.demo.todos.service;

import com.example.demo.config.service.MemberLoginService;
import com.example.demo.todos.domain.dto.TodoCondition;
import com.example.demo.todos.domain.entity.Todo;
import com.example.demo.todos.domain.request.TodoRequest;
import com.example.demo.todos.domain.response.TodoResponse;
import com.example.demo.todos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//로그인 시간 판별해서, 유저가 로그인이 되어있는지 확인하려고 하는것

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberLoginService memberLoginService;
    public void insert(TodoRequest request){
//        memberLoginService.findByMember(request.getMemberId());
        todoRepository.save(request.toEntity());
    }
    //PUT {todoId}/check (isDone -> true)
//202
//404
//TODOS_NOT_FOUND
//401
//CHECK LOGIN USRE
    @Transactional
    public void check(Long todoId, Long memberId){
        Optional<Todo> byId = todoRepository.findById(todoId);
        Todo todo = byId
                .orElseThrow(() -> new RuntimeException("TODOS NOT FOUND"));
        if(!todo.getMember().getId().equals(memberId))
            throw new RuntimeException("MEMBER NOT FOUND");
        todo.changeIsDone();
    }
    @Transactional(readOnly = true)
    public Page<TodoResponse> getAll(
            PageRequest request, TodoCondition condition){
//        Page<Todo> allByCondition = todoRepository
//                .findAllByCondition(request, condition);
//        return allByCondition
//                .map(TodoResponse::new);
        return todoRepository
                .findAllByCondition(request, condition);
    }



}