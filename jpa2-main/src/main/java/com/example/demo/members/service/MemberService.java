package com.example.demo.members.service;

import com.example.demo.config.auth.AuthService;
import com.example.demo.config.domain.dto.MemberDto;
import com.example.demo.config.domain.entity.MemberLogin;
import com.example.demo.config.exception.ExistEmailException;
import com.example.demo.config.exception.LoginFailException;
import com.example.demo.config.service.MemberLoginService;
import com.example.demo.members.domain.entity.Member;
import com.example.demo.members.domain.request.LoginRequest;
import com.example.demo.members.domain.request.SignupRequest;
import com.example.demo.members.domain.response.LoginResponse;
import com.example.demo.members.domain.response.MemberResponse;
import com.example.demo.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberLoginService memberLoginService;
    private final AuthService authService;

    public void insert(SignupRequest request){
        Optional<Member> byEmail = memberRepository.findByEmail(request.email());
        if(byEmail.isPresent()) throw new ExistEmailException("있는 거");
        memberRepository.save(request.toEntity());
    }
    @Transactional
    public LoginResponse login(LoginRequest request){
        Optional<Member> byEmailAndPassword =
                memberRepository
                        .findByEmailAndPassword(request.email(), request.password());
        Member member = byEmailAndPassword
                .orElseThrow(() -> new LoginFailException("없는 유저"));
        memberLoginService.insert(member);
        String token = authService.makeToken(member);
        return new LoginResponse(member.getId(), member.getName(), member.getAge(), token);
    }

    public Page<MemberResponse> findAll(PageRequest request){
        Page<Member> allBy = memberRepository.findAllFetch(request);
        return allBy.map(MemberResponse::new);
    }
    public Map<String, Object> getTokenToData(String token){
        return authService.getClaims(token);
    }

}