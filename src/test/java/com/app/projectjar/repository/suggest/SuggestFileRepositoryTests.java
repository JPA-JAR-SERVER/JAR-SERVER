//package com.app.projectjar.repository.suggest;
//
//import com.app.projectjar.entity.file.suggest.SuggestFile;
//import com.app.projectjar.entity.suggest.Suggest;
//import com.app.projectjar.repository.file.suggest.SuggestFileRepository;
//import com.app.projectjar.repository.member.MemberRepository;
//import com.app.projectjar.type.BoardType;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.util.UUID;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class SuggestFileRepositoryTests {
//
//    @Autowired
//    private SuggestRepository suggestRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private SuggestFileRepository suggestFileRepository;
//
////    @Test
////    public void saveTest() {
////        memberRepository.findById(1L).ifPresent(
////                member ->
////                        suggestRepository.findById(89L).ifPresent(
////                                suggest -> {
////                                    for (int i = 0; i < 10; i++) {
////                                        suggestFileRepository.save(new SuggestFile("테스트" + (i + 1) + ".png", UUID.randomUUID().toString(), "2023/05/" + (i + 1), suggest));
////                                    }
////                                }
////                        )
////        );
////    }
//}
