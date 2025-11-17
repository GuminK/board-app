package com.example.backend;

import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() throws Exception {
        Member member = new Member();
        member.setMemberName("memberA");

        memberRepository.save(member);
//        Member findMember = memberRepository.findById(member.getId()).get();
        Member findMember = null;

        if (memberRepository.findById(member.getId()).isPresent()) {
            findMember = memberRepository.findById(member.getId()).get();
        }

        org.junit.jupiter.api.Assertions.assertNotNull(findMember);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getMemberName()).isEqualTo(member.getMemberName());
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}
