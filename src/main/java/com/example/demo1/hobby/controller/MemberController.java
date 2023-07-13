package com.example.demo1.hobby.controller;

import com.example.demo1.hobby.domain.request.SaveMemberDto;
import com.example.demo1.hobby.domain.entity.Member;
import com.example.demo1.hobby.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    // MemberService 인스턴스를 사용하기 위해 필드로 선언, final은 한 번 초기화 후 변경하지 않겠다는 의미
    public final MemberService service;

    // 회원 정보 저장 기능
    @PostMapping
    public void save(@RequestBody SaveMemberDto memberDto) {
        service.save(memberDto);
        //이거는 저장만 하니까 리턴을 안하는거
    }

    // ID에 해당하는 회원 정보 조회 기능
    @GetMapping("{id}")
    public Member findById(@PathVariable("id") Long id) {
        return service.findById(id);
        // 회원 정보 조회니까 보여줘야 하잖아?
    }

    // ID에 해당하는 회원 정보 삭제 기능
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
        // 삭제를 하니 리턴을 하면 안된다.
    }

    // ID에 해당하는 회원 정보 업데이트 기능`
    @PutMapping("{id}")
    public Member updateMember(@PathVariable("id") Long id, @RequestBody SaveMemberDto saveMemberDto){
        return service.updateMember(id, saveMemberDto);
        //업데이트를 했으니 보여줘야지?
    }
}



