package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberQueryDsl {

    // 회원탈퇴
    public void withDraw_QueryDsl(Long memberId);

    /* 로그인 */

    public Member findById_QueryDSL(Long id);

//    이메일 중복 검사
    public Long overlapByMemberEmail_QueryDSL(String memberEmail);

//    휴대폰 중복 검사
    public Long overlapByPhoneNumber_QueryDSL(String memberPhoneNumber);

//    닉네임 중복검사
    public Long overlapByNickName_QueryDSL(String memberNickName);

//    비밀 번호 찾기
    public Optional<Member> findByMemberEmailForPassword_QueryDSL(String memberEmail);

//    비밀 번호 변경
    public void updatePassword_QueryDSL(Long id, String memberPassword);

//    회원 멤버 조회
    public Optional<Member> findByMemberId_QueryDSL(Long id);

//    이메일로 회원 조회
    public Optional<Member> findByMemberEmail_QueryDSL(String memberEmail);

//    그냥 이메일 조회
    public Member findByMemberEmailNoOptional_QueryDSL(String memberEmail);

    /* 랜덤키로 계정 찾기 */
    public Member findMemberByRandomKey(String randomKey);

    /* 랜덤키로 계정 찾기 */
    public Member findMemberByMemberEmailAndRandomKey(String memberEmail, String randomKey);

    /* 마이 페이지 */

//    회원 정보 수정
    public void updateMember_QueryDSL(Member memberInfo);

//    프로필 이미지
//    public void updateMemberFile(Member member);

//    회원 삭제
    public void deleteMemberById_QueryDSL(Long id);

    /* 뱃지 */

//    챌린지 횟수 조회
    public int findByIdWithAttendCount_QueryDsl(Long id);

//    뱃지 업데이트 ( 개인 챌린지 어탠드 카운트 + 그룹 챌린지 어탠드 카운트 )  => 그러면 attend.member로 접근해서 카운트 세야되는건가?
    public void updateMemberBadge_QueryDSL(Long id);


    //    관리자 페이지 회원 전체 조회
    public Page<Member> findAllByMemberId_QueryDsl(Pageable pageable);
//  회원 정보 수정
    public void updateMemberAdmin_QueryDSL(Member memberInfo);

    public Long findPersonalAttendCountByMemberId(Long id);

    public Long findGroupAttendCountByMemberId(Long id);
}
