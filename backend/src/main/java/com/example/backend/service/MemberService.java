package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.dto.member.LoginInfoDto;
import com.example.backend.dto.member.MemberResponseDTO;
import com.example.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public Member findMemberByMemberIdAndMemberPw(String memberId, String memberPw){
        return memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
    }

    public boolean userLogin(LoginInfoDto data){
        Member findMember = memberRepository.findByMemberId(data.getMemberId());
        if(findMember == null){
            return false;
        }
        // 아이디에 맞는 비밀번호 체크
        return passwordEncoder.matches(data.getMemberPw(), findMember.getMemberPw());
//        return findMember.getMemberPw().equals(data.getMemberPw());
    }

    public String userRegister(MemberResponseDTO data){
        Member findMember = memberRepository.findByMemberId(data.getMemberId());
        if(findMember == null){
            // 전달 받은 data의 비밀번호 encode
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
