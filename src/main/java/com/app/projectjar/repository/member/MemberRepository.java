package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
    //    이메일로 전체 정보 조회 (MemberService)
    public Optional<Member> findByMemberEmail(String memberEmail);
    public Optional<Member> findByMemberNickname(String memberNickname);
    public Optional<Member> findByMemberPhoneNumber(String memberEmail);

    public Member findMemberById(Long id);

}
