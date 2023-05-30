//package com.app.projectjar.repository.suggest;
//
//import com.app.projectjar.entity.suggest.SuggestReply;
//import com.app.projectjar.repository.file.member.MemberFileRepository;
//import com.app.projectjar.repository.member.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class SuggestReplyRepositoryTests {
//
//    @Autowired
//    private SuggestReplyRepository suggestReplyRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private SuggestRepository suggestRepository;
//
//    @Autowired
//    private MemberFileRepository memberFIleRepository;
//
////    @Test
////    public void test() {
////        memberRepository.findById(2L).ifPresent(
////                member ->
////                        memberFIleRepository.save(new MemberFile(
////                                "테스트.png",
////                                UUID.randomUUID().toString(),
////                                "2023/05/06",
////                                REPRESENTATIVE,
////                                member
////                        ))
////        );
////    }
//
//
//    @Test
//    public void saveTest() {
//        suggestRepository.findById(11L).ifPresent(
//                suggest ->
//                        memberRepository.findById(1L).ifPresent(
//                                member -> {
//                                    for (int i = 0; i < 10; i++) {
//                                        suggestReplyRepository.save(new SuggestReply("테스트 댓글" + (i + 1), suggest, member));
//                                    }
//                                }
//                                )
//        );
//    }
//
//    @Test
//    public void findAllBySuggestWithPagingTest() {
//        PageRequest pageRequest = PageRequest.of(0,5);
//        suggestReplyRepository.findAllBySuggestWithPaging_QueryDsl(11L,pageRequest).forEach(suggestReply -> log.info(suggestReply.toString()));
//    }
//
//    @Test
//    public void updateTest() {
//        suggestReplyRepository.findById(104L).ifPresent(
//                suggestReply -> suggestReply.setSuggestReplyContent("수정된 댓글1")
//        );
//    }
//
//    @Test
//    public void deleteTest() {
//        suggestReplyRepository.findById(104L).ifPresent(
//                suggestReply -> suggestReplyRepository.delete(suggestReply)
//        );
//    }
//
//    @Test
//    public void countTest(){
//        suggestRepository.findById(2L).ifPresent(
//                suggest -> log.info(suggestReplyRepository.getReplyCount_QueryDsl(suggest.getId())+ "개수")
//        );
//    }
//}
