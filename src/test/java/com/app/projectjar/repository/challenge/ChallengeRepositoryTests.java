//package com.app.projectjar.repository.challenge;
//
//import com.app.projectjar.entity.challenge.Challenge;
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
//public class ChallengeRepositoryTests {
//
//    @Autowired
//    private ChallengeRepository challengeRepository;
//
//    @Test
//    public void saveTest(){
//
//        for (int i = 0; i < 100; i++) {
//            Challenge challenge = Challenge.builder()
//                    .boardContent("테스트 내용 " + (i + 1))
//                    .boardTitle("챌린지 테스트 제목 " + (i + 1))
//                    .build();
//
//            challengeRepository.save(challenge);
//        }
//
//    }
//
//    @Test
//    public void findAll_QueryDslTest() {
//        challengeRepository.findAll_QueryDsl().stream().map(Challenge::toString).forEach(log::info);
//    }
//
//}
