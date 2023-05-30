//package com.app.projectjar.repository.notice;
//
//import com.app.projectjar.entity.notice.Notice;
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
//public class NoticeRepositoryTests {
//    @Autowired
//    private NoticeRepository noticeRepository;
////  공지사항 작성하기
//    @Test
//    public void saveTest(){
//        for (int i = 0; i < 37; i++) {
//            noticeRepository.save(Notice.builder().noticeTitle("테스트용 제목" + i).noticeContent("테스트용 내용" + i).build());
//        }
//    }
//
////    공지사항 리스트
//    @Test
//    public void findAllWithPagingTest() {
//        PageRequest pageRequest = PageRequest.of(0,10);
//        noticeRepository.findAllWithPaging_QueryDSL(pageRequest).stream().map(Notice::toString).forEach(log::info);
//    }
//
////    공지사항 상세보기
//    @Test
//    public void findByNoticeIdTest(){
//        noticeRepository.findById(2L).map(Notice::toString).ifPresent(log::info);
//    }
//
////    공지사항 삭제하기
//    @Test
//    public void deleteTest() {
//        noticeRepository.findById(2L).ifPresent(notice -> noticeRepository.delete(notice));
//    }
//
////    공지사항 수정하기
//    @Test
//    public void updateTest(){
//        noticeRepository.findById(2L).ifPresent(
//                notice -> {notice.setNoticeTitle("수정된 제목2");
//                notice.setNoticeContent("수정된 내용 2");}
//        );
//    }
//}
