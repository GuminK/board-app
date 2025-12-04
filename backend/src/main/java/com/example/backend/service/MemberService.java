package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 사용자 회원가입
    public void registerMember(Member member){
        memberRepository.save(member);
    }


    public Member findMemberByMemberIdAndMemberPw(String memberId, String memberPw){
        return memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
    }
}
