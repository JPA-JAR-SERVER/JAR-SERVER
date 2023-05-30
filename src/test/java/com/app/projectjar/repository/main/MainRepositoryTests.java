//package com.app.projectjar.repository.main;
//
//import com.app.projectjar.repository.challenge.ChallengeRepository;
//import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
//import com.app.projectjar.repository.member.MemberRepository;
//import com.app.projectjar.repository.suggest.SuggestLikeRepository;
//import com.app.projectjar.repository.suggest.SuggestReplyRepository;
//import com.app.projectjar.repository.suggest.SuggestRepository;
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
//public class MainRepositoryTests {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private ChallengeRepository challengeRepository;
//
//    @Autowired
//    private GroupChallengeRepository groupChallengeRepository;
//
//    @Autowired
//    private SuggestRepository suggestRepository;
//
//    @Autowired
//    private SuggestLikeRepository suggestLikeRepository;
//
//    @Autowired
//    private SuggestReplyRepository suggestReplyRepository;
//
////    그룹 챌린지 리스트
//    @Test
//    public void findAll() {
//        groupChallengeRepository.findAll().forEach(groupChallenge -> log.info(String.valueOf(groupChallenge)));
//    }
//
//}
