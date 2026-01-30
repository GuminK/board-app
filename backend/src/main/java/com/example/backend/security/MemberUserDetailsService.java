package com.example.backend.security;

import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberUserDetailsService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member m = memberRepository.findByMemberId(username);
        if(m==null) throw new UsernameNotFoundException("memberId not found: " + username);

        String role =  (m.getRoleType() == null || m.getRoleType().isBlank()) ? "USER" : m.getRoleType();

        return new org.springframework.security.core.userdetails.User(
                m.getMemberId(),
                m.getMemberPw(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}
