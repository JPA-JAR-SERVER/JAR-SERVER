//package com.app.projectjar.repository.member;
//
//import com.app.projectjar.entity.member.Member;
//import com.app.projectjar.entity.member.MemberRandomKey;
//import com.app.projectjar.repository.file.member.MemberFileRepository;
//import com.app.projectjar.repository.groupChallenge.GroupChallengeAttendRepository;
//import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
//import com.app.projectjar.service.memberRandomKey.MemberRandomKeyService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class MemberRepositoryTests {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private MemberFileRepository memberFIleRepository;
//
//    @Autowired
//    private GroupChallengeAttendRepository groupChallengeAttendRepository;
//
//    @Autowired
//    private GroupChallengeRepository groupChallengeRepository;
//
//    @Autowired
//    private MemberRandomKeyRepository memberRandomKeyRepository;
//
//    @Autowired
//    private MemberRandomKeyService memberRandomKeyService;
//
//    @Test
//    public void randomkeyTest(){
//        Member member = memberRepository.findMemberById(108L);
//        MemberRandomKey memberRandomKey = memberRandomKeyRepository.getListRandomKey(member.getId());
//
//        if(memberRandomKey == null){
//            log.info("YesNull");
//        }else{
//            log.info("NoNull");
//        }
//
//    }
//
//    @Test
//    public void randomkeyTest2(){
//        Member member = memberRepository.findMemberById(108L);
//        memberRandomKeyService.saveRandomKey(member);
////        MemberRandomKey memberRandomKey2 = memberRandomKeyRepository.getListRandomKey(member.getId());
//
////        if(memberRandomKey2 == null){
////            log.info("YesNull");
////        }else{
////            log.info("NoNull");
////        }
//
//    }
//
//
////      추가
//    @Test
//    public void saveTest() {
////        MemberFile memberFile = new MemberFile("", "", "", FileType.REPRESENTATIVE);
////        Member member = new Member(1L, "user03@naver.com", "qwer1234!", "010-5634-1278", "정용준", "대구시민", MemberType.ENABLE, BadgeType.ONE, Role.MEMBER, null);
////        memberRepository.save(member);
////        memberFIleRepository.save(memberFile);
//    }
//
////      조회
//    @Test
//    public void findByMemberId_QueryDSLTest() {
//        memberRepository.findByMemberId_QueryDSL(1L).map(Member::toString).ifPresent(log::info);
//    }
//
////    @Test
////    public void findAllWithPageAndChallenges_QueryDslTest(){
////        PageRequest pageRequest = PageRequest.of(0,10);
////        Long memberId = 1L;
////        memberRepository.findAllWithPageAndChallenges_QueryDsl(memberId, pageRequest).map(ChallengeAttend::toString).forEach(log::info);
////    }
//
//
////    수정
//    @Test
//    public void updateMemberTest(){
//        memberRepository.findByMemberId_QueryDSL(1L).ifPresent(member -> {
//            member.setMemberName("규선최");
//            member.setMemberPhoneNumber("010-9999-8888");
//            member.setMemberNickname("최규선규선");
//            });
//
//    }
//
////      삭제
//    @Test
//    public void deleteMemberTest(){
//        memberRepository.findByMemberId_QueryDSL(3L).ifPresent(memberRepository::delete);
//    }
//
////    이메일 중복 검사
////    @Test
////    public void overlapByMemberEmailTest(){
//////        memberRepository.overlapByMemberEmail("user01@naver.com").ifPresent(member -> member.getMemberEmail());
////        memberRepository.overlapByMemberEmail_QueryDSL("user04@naver.com").map(Member::getMemberEmail).ifPresent(log::info);
////        log.info("@@@@@@@@@@@@@@@@@@");
////    }
////
//////    핸드폰 중복 검사
////    @Test
////    public void overlapByPhoneNumberTest(){
////        memberRepository.overlapByPhoneNumber_QueryDSL("010-4444-5678").map(Member::getMemberPhoneNumber).ifPresent(log::info);
////        log.info("@@@@@@@@@@@@@@@@@@");
////    }
//
////    비밀번호 찾기
//    @Test
//    public void findByMemberEmailForPasswordTest(){
//        memberRepository.findByMemberEmailForPassword_QueryDSL("user01@naver.com").map(Member::getMemberEmail).ifPresent(log::info);
//    }
//
////    비밀번호 변경
//    @Test
//    public void updatePasswordTest(){
//        memberRepository.updatePassword_QueryDSL(1L, "qwer1234@");
//    }
//
////    챌린지 횟수 조회
//    @Test
//    public void findByIdWithAttendCount_QueryDslTest(){
//        log.info(""+memberRepository.findByIdWithAttendCount_QueryDsl(1L));
//    }
//
////    뱃지 타입 업데이트
////    @Test
////    public void updateMemberBadgeTest(){
////        memberRepository.updateMemberBadge_QueryDSL(1L, BadgeType.ONE);
////    }
//
//
//
//
//
//
////        @Test
////        void findAllWithPageAndGroupChallenges_QueryDslTest() {
////            Long memberId = 5L;
////            PageRequest pageable = PageRequest.of(0, 10);
////
////            Page<GroupChallengeAttend> resultPage = memberRepository.findAllWithPageAndGroupChallenges_QueryDsl(memberId, pageable);
////
////            List<GroupChallengeAttend> groupChallengeAttendList = resultPage.getContent();
////            for (GroupChallengeAttend groupChallengeAttend : groupChallengeAttendList) {
////                log.info(groupChallengeAttend.toString());
////            }
////
////            long totalCount = resultPage.getTotalElements();
////            System.out.println("Total Count: " + totalCount);
////        }
//
//    }
//
//
