package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface DiaryQueryDsl {

    // save
    // 상세보기
    public Optional<Diary> findByDiaryId_QueryDsl(Long diaryId);
    // 리스트
    public Slice<Diary> findByMemberIdDiary_QueryDsl(String sort, Pageable pageable);
    // 전체 조회
    public Page<Diary> findAllWithPaging_QueryDSL(Pageable pageable);


    /* 마이 페이지 */
//    다이어리 목록
    public List<Diary> findByMemberIdDiary_QueryDsl(Long memberId);


//    내가 작성한 다이어리 게시판 전체 조회
    public Page<Diary> findAllByMemberWithPaging_QueryDsl(Pageable pageable, Long id);

//    내가 좋아요한 다이어리 게시판 전체조회
    public Page<Diary> findByLikeMEmberIdWithPaging_QueryDsl(Pageable pageable, Long id);
 
//      현재 시퀀스 가져오기
    public Diary getCurrentSequence_QueryDsl();

//    다이어리 삭제
    public void deleteByDiaryId_QueryDsl(Long diaryId);

}
