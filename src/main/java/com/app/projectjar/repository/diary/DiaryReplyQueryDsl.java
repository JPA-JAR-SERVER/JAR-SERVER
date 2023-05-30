package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.DiaryReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryReplyQueryDsl {

    // 댓글 저장 save
    // 댓글 수정 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 댓글 삭제 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 전체 조회 ( 페이징 )
    public Slice<DiaryReply> findAllByDiaryWithPaging_QueryDsl(Long diaryId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long diaryId);
    // 삭제
    public void deleteByDiaryId(Long diaryId);
}
