//package com.app.projectjar.repository.suggest;
//
//import com.app.projectjar.entity.suggest.Suggest;
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
//public class SuggestRepositoryTests {
//
//    @Autowired
//    private SuggestRepository suggestRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//
////    @Test
////    public void saveTest() {
////        for (int i = 0; i < 100; i++) {
////            memberRepository.findById(1L).ifPresent(
////                    member ->
////                            suggestRepository.save(new Suggest("테스트 제목","테스트 내용",BoardType.PERSONAL,member)));
////        }
////    }
//
////    @Test
////    public void findAllWithPagingTest() {
////        PageRequest pageRequest = PageRequest.of(0,10);
////        suggestRepository.findByGroupWithPaging_QueryDsl(pageRequest).stream().map(Suggest::toString).forEach(log::info);
////    }
////
////    @Test
////    public void findByIdSuggest() {
////        suggestRepository.findByIdSuggest_QueryDsl(26L).map(Suggest::toString).ifPresent(log::info);
////    }
////
////    @Test
////    public void getCurrentSequenceTest() {
////        log.info(suggestRepository.getCurrentSequence() + "");
////    }
////
////    /*관리자 페이지*/
////    //    제안 게시판 삭제하기
////    @Test
////    public void deleteTest() {
////        suggestRepository.findById(2L).ifPresent(notice -> suggestRepository.delete(notice));
////    }
////
//////     메인 페이지 페이징 없이 조회
////    @Test
////    public void findAllByPersonal_QueryDslTest(){
////        suggestRepository.findByPersonal_QueryDsl().stream().map(Suggest::toString).forEach(log::info);
////        log.info("@@@@@@@@@");
////    }
////
////    /* 마이 페이지 */
////    @Test
////    public void findAllByMemberIdWithPaging_QueryDslTest(){
////        PageRequest pageRequest = PageRequest.of(1, 5);
////        suggestRepository.findAllByMemberIdWithPaging_QueryDsl(pageRequest,1L).stream().map(Suggest::toString).forEach(log::info);
////        log.info("@@@@@@@@@");
////    }
////    @Test
////    public void deleteTest(){
////        suggestRepository.findById(35L).ifPresent(
////                suggest -> suggestRepository.delete(suggest)
////        );
////    }
//}
