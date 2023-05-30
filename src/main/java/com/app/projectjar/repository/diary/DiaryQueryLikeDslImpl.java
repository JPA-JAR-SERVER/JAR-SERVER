package com.app.projectjar.repository.diary;


import com.app.projectjar.entity.diary.DiaryLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.projectjar.entity.diary.QDiaryLike.diaryLike;

@RequiredArgsConstructor
public class DiaryQueryLikeDslImpl implements DiaryQueryLikeDsl {
    private final JPAQueryFactory query;


    @Override
    public Long findMemberByDiaryLike_QueryDsl(Long diaryId, Long memberId) {
        return query.select(diaryLike.member.count())
                .from(diaryLike)
                .where(diaryLike.diary.id.eq(diaryId).and(diaryLike.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public Long getDiaryLikeCount_QueryDsl(Long diaryId) {
        return query.select(diaryLike.count())
                .from(diaryLike)
                .where(diaryLike.diary.id.eq(diaryId))
                .fetchOne();
    }

    @Override @Transactional
    public void deleteByMemberIdAndDiaryId_QueryDsl(Long diaryId, Long memberId) {
        query.delete(diaryLike)
                .where(diaryLike.member.id.eq(memberId).and(diaryLike.diary.id.eq(diaryId)))
                .execute();
    }

    @Override
    public void deleteByDiaryId(Long diaryId) {
        query.delete(diaryLike)
                .where(diaryLike.diary.id.eq(diaryId))
                .execute();
    }

    @Override
    public Page<DiaryLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long memberId) {
        List<DiaryLike> foundDiaries = query.select(diaryLike)
                .from(diaryLike)
                .where(diaryLike.member.id.eq(memberId))
                .orderBy(diaryLike.diary.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(diaryLike.count())
                .from(diaryLike)
                .where(diaryLike.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundDiaries, pageable, count);

    }

}
