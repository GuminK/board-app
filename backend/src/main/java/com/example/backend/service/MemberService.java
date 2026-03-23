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

    // 사용자 회원가입
    public void registerMember(Member member){
        memberRepository.save(member);
    }

    public Optional<Member> findMemberById(Long id){
        return memberRepository.findById(id);
    }

    public Member findMemberByMemberId(String memberId){
        return memberRepository.findByMemberId(memberId);
    }

    public String userRegister(RegisterRequest data){
        Member findMember = memberRepository.findByMemberId(data.getMemberId());
        if(findMember == null){
            // 전달 받은 data의 비밀번호 encode
            String encodePw = passwordEncoder.encode(data.getMemberPw());

            // 일단 roleType을 USER로 저장
            Member member = Member.builder()
                    .memberId(data.getMemberId())
                    .memberPw(encodePw)
                    .memberName(data.getMemberName())
                    .roleType("USER")
                    .build();
            memberRepository.save(member);
            return member.getMemberName();
        }
        else { // 이미 해당 ID로 가입된 유저가 있을 때
            return "already exist";
        }
    }

//    public Member findByIdOrThrow(Long id){
//        return memberRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + id));
//    }
}
