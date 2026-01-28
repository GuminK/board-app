package com.example.backend.controller.login;

import com.example.backend.dto.member.LoginInfoDto;
import com.example.backend.dto.member.MemberResponseDTO;
import com.example.backend.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginInfoDto data){
        if(memberService.userLogin(data)){
            return "login success";
        }
        return "login fail";
    }

    @PostMapping("/register")
    public String userRegister(@RequestBody MemberResponseDTO data){
        // 넘어온 data로 회원가입 시도
        String msg = memberService.userRegister(data);
        if(msg.equals("already exist")){
            return "register fail, already exist";
        }
        return "register success";
    }

}
