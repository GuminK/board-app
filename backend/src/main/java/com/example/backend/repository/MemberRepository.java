package com.example.backend.repository;

import com.example.backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberIdAndMemberPw(String memberId, String memberPw);
    Member findByMemberId(String memberId);
}
