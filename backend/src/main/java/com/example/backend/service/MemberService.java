package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.dto.auth.LoginRequest;
import com.example.backend.dto.auth.RegisterRequest;
import com.example.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member findMemberByMemberId(String memberId){
        return memberRepository.findByMemberId(memberId);
    }

    // 회원가입
    public String userRegister(RegisterRequest data){
        Member findMember = memberRepository.findByMemberId(data.getMemberId());
        if(findMember == null){
            String encodePw = passwordEncoder.encode(data.getMemberPw());

            Member member = Member.builder()
                    .memberId(data.getMemberId())
                    .memberPw(encodePw)
                    .memberName(data.getMemberName())
                    .roleType("USER")
                    .build();
            memberRepository.save(member);
            return member.getMemberName();
        }
        else {
            return "already exist";
        }
    }

}
