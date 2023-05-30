//package com.app.projectjar.repository.diary;
//
//import com.app.projectjar.entity.diary.Diary;
//import com.app.projectjar.repository.member.MemberRepository;
//import com.app.projectjar.type.DiaryType;
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
//public class DiaryRepositoryTests {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DiaryRepository diaryRepository;
//
////    @Test
////    public void saveTest() {
////        memberRepository.findById(13L).ifPresent(
////                member ->{
////                    for (int i = 0; i < 20; i++) {
////                        diaryRepository.save(
////                                new Diary(
////                                        "테스트 제목" + (i + 1),
////                                        "테스트 내용" + (i + 1),
////                                        DiaryType.OPEN,
////                                        member
////                                )
////                        );
////                    }
////                }
////        );
////    }
//
//    @Test
//    public void findByDiaryId() {
//        diaryRepository.findByDiaryId_QueryDsl(110L).map(Diary::toString).ifPresent(log::info);
//    }
//
//    @Test
//    public void findByMemberId_QueryDslTest(){
//        diaryRepository.findByMemberIdDiary_QueryDsl(1L).stream().map(Diary::toString).forEach(log::info);
//    }
//
////    @Test
////    public void findAllDiaryTest() {
////        PageRequest pageRequest = PageRequest.of(1,10);
////        diaryRepository.findAllDiary_QueryDsl(pageRequest).stream().map(Diary::toString)
////                .forEach(log::info);
////    }
//
//    @Test
//    public void findAllByMemberWithPaging_QueryDslTest(){
//        PageRequest pageRequest = PageRequest.of(1,5);
//        diaryRepository.findAllByMemberWithPaging_QueryDsl(pageRequest, 1L).stream().map(Diary::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findAllDiary_QueryDslTest() {
//        diaryRepository.findByMemberIdDiary_QueryDsl("popular",PageRequest.of(0,10)).map(Diary::toString).forEach(log::info);
//    }
//}
