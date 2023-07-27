package com.example.demo.members.controller;

import com.example.demo.config.domain.entity.MemberLogin;
import com.example.demo.config.repository.MemberLoginRepository;
import com.example.demo.members.domain.entity.Member;
import com.example.demo.members.domain.request.LoginRequest;
import com.example.demo.members.domain.request.SignupRequest;
import com.example.demo.members.repository.MemberRepository;
import com.example.demo.todos.domain.entity.Todo;
import com.example.demo.todos.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MembersControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

//    @MockBean
//    MemberService memberService;

    @Test
    void 로그인_성공() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest(email, password);
//        Mockito.when(memberService.login(ArgumentMatchers.any(LoginRequest.class)))
//                        .thenReturn(new LoginResponse(1l, "name",12));
        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest))
                )
                .andExpect(
                        status().isOk())
                .andExpect(
                        jsonPath("$.id").isNotEmpty())
                .andExpect(
                        jsonPath("$.name").value("name"))
                .andExpect(
                        jsonPath("$.age").value(10));
    }

    @Test
    void 로그인_실패() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest(email+"111", password);
//        Mockito.when(memberService.login(ArgumentMatchers.any(LoginRequest.class)))
//                .thenThrow(new LoginFailException("aaa"));

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest))
                )
                .andExpect(
                        status().isBadRequest());
    }
    @Test
    void 가입_성공() throws Exception {
        SignupRequest signupRequest =
                new SignupRequest(email+"111", password,"name",10);
//        Mockito.doNothing()
//                .when(memberService)
//                .insert(ArgumentMatchers.any(SignupRequest.class));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupRequest))
                )
                .andExpect(
                        status().isCreated());
    }

    @Test
    void 가입_실패() throws Exception {
        SignupRequest signupRequest =
                new SignupRequest(email, password,"name",10);
//        Mockito.doThrow(new ExistEmailException("있는 거"))
//                .when(memberService)
//                .insert(ArgumentMatchers.any(SignupRequest.class));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupRequest))
                )
                .andExpect(
                        status().isBadRequest());
//                .andExpect(
//                        MockMvcResultMatchers
//                                .content()
//                                .string("있는 거"));
    }

    @Test
    void 멤버_모두_가져오기() throws Exception{
        // 넣는 곳
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/members")
                )
                .andExpect(
                        status().isOk())
                .andExpect(jsonPath("$.content.[0].id").isNotEmpty())
                .andExpect(jsonPath("$.content.[0].name")
                        .value("name"))
                .andExpect(jsonPath("$.content.[0].email")
                        .value(email))
                .andExpect(jsonPath("$.content.[0].age")
                        .value(10))
                .andExpect(jsonPath("$.content.[0].todos").
                        isArray())
                .andExpect(jsonPath("$.content.[0].todos.[0].title")
                        .value("t"))
                .andExpect(jsonPath("$.content.[0].todos.[1].title")
                        .value("t2"))
        ;

    }
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    MemberLoginRepository memberLoginRepository;
    String email = "1111";
    String password = "1234";
    Member member;
    @Autowired
    EntityManager entityManager;
    @BeforeEach
    void init(){
        Member member =
                new Member(null, email, password
                        , "name", 10, new ArrayList<>(), null);

        this.member = memberRepository.save(member);
        todoRepository.save(
                new Todo(null, "t","t"
                        , false,0, member)
        );
        todoRepository.save(
                new Todo(null, "t2","t2"
                        , false,0, member)
        );
        MemberLogin entity = new MemberLogin(this.member, LocalDateTime.now());
        memberLoginRepository.save(entity);
        entityManager.flush();
        entityManager.clear();

    }
    @AfterEach
    void clean(){
        todoRepository.deleteAll();
        memberLoginRepository.deleteAll();
        memberRepository.deleteAll();
    }

}