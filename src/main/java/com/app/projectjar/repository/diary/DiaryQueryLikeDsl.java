package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.DiaryLike;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryQueryLikeDsl {

    public Long findMemberByDiaryLike_QueryDsl(Long diaryId, Long memberId);

    public Long getDiaryLikeCount_QueryDsl(Long diaryId);

    public void deleteByMemberIdAndDiaryId_QueryDsl(Long diaryId, Long memberId);

    public void deleteByDiaryId(Long diaryId);

    public Page<DiaryLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long memberId);
}
