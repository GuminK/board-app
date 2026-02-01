package com.example.backend.controller;

import com.example.backend.domain.Member;
import com.example.backend.dto.member.MemberDTO;
import com.example.backend.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class ApiController {
    private final MemberService memberService;

    public ApiController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/api/time")
    public ResponseEntity<String> getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ResponseEntity.ok().body(LocalDateTime.now().format(dtf));
    }

    @GetMapping("/api/member/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id){
        return memberService.findMemberById(id)
                .map(m -> new MemberDTO(m.getMemberName(), m.getMemberId(), m.getRoleType()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
