//package com.app.projectjar.repository.diary;
//
//import com.app.projectjar.entity.diary.DiaryLike;
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
//public class DiaryLikeRepositoryTests {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DiaryRepository diaryRepository;
//
//    @Autowired
//    private DiaryLikeRepository diaryLikeRepository;
//
//
////    @Test
////    public void saveTest() {
////        diaryRepository.findById(110L).ifPresent(
////                diary ->
////                    memberRepository.findById(1L).ifPresent(
////                            member ->
////                                    diaryLikeRepository.save(new DiaryLike(diary, member))
////                    )
////        );
////    }
//
////    @Test
////    public void findMemberByDiaryLikeTest() {
////        diaryRepository.findById(110L).ifPresent(
////                diary ->
////                        memberRepository.findById(1L).ifPresent(
////                                member ->
////                                        diaryLikeRepository.findMemberByDiaryLike(diary.getId(), member.getId())
////                        )
////        );
////    }
//
////    @Test
////    public void getDiaryLikeCountTest() {
////        diaryRepository.findById(110L).ifPresent(
////             diary ->
////             log.info(diaryLikeRepository.getDiaryLikeCount(diary.getId()) + "개")
////        );
////    }
////
////    @Test
////    public void deleteByMemberIdAndDiaryIdTest() {
////        diaryRepository.findById(110L).ifPresent(
////                diary ->
////                        memberRepository.findById(1L).ifPresent(
////                                member ->
////                                diaryLikeRepository.deleteByMemberIdAndDiaryId(diary.getId(), member.getId())
////                        )
////        );
////    }
//
//        @Test
//    public void findByLikeMemberIdWithPaging_QueryDsl(){
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        diaryLikeRepository.findByLikeMemberIdWithPaging_QueryDsl(pageRequest, 1L).stream().map(DiaryLike::toString).forEach(log::info);
//        log.info("@@@@@@@@@");
//    }
//
//
//}
