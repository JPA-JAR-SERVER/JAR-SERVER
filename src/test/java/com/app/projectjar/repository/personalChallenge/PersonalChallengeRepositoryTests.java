//package com.app.projectjar.repository.personalChallenge;
//
//import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
//import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
//import com.app.projectjar.repository.challenge.ChallengeRepository;
//import com.app.projectjar.type.ChallengeType;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.awt.print.Pageable;
//import java.time.LocalDate;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class PersonalChallengeRepositoryTests {
//
//    @Autowired
//    private PersonalChallengeRepository personalChallengeRepository;
//
//    @Autowired
//    private ChallengeRepository challengeRepository;
//
//    @Autowired
//    private ChallengeAttendRepository challengeAttendRepository;
//
//    @Test
//    public void saveTest(){
//        for (int i = 171; i < 181; i++) {
//            challengeRepository.findById(Long.valueOf(i)).ifPresent(
//                    challenge -> {
//                        PersonalChallenge personalChallenge = PersonalChallenge.builder()
//                                .challenge(challenge)
//                                .challengeStatus(ChallengeType.PRIVATE)
//                                .build();
//                        personalChallengeRepository.save(personalChallenge);
//                    }
//            );
//        }
//    }
//
//    @Test
//    public void findAllByChallengeStatusToOpenTest(){
//        personalChallengeRepository.findAllByChallengeStatus("OPEN", PageRequest.of(0,12)).stream().map(PersonalChallenge::getChallengeStatus).forEach(challengeType -> log.info(challengeType + ""));
//    }
//
//    @Test
//    public void findByCreateDateYesterdayTest(){
//        personalChallengeRepository.findByCreateDateYesterday(LocalDate.now().minusDays(1)).stream().map(PersonalChallenge::toString).forEach(log::info);
//    }
//
////    @Test
////    public void findAllWithPageAndChallenges_QueryDslTest() {
////        challengeAttendRepository.findAllWithPageAndChallenges_QueryDsl(233L, PageRequest.of(0,10)).stream()
////                .map(ChallengeAttend::toString).forEach(log::info);
////    }
//}
