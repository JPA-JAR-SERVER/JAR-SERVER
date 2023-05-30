package com.app.projectjar.repository.diary;


import com.app.projectjar.entity.diary.DiaryReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.projectjar.entity.diary.QDiaryReply.diaryReply;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class DiaryReplyQueryDslImpl implements DiaryReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<DiaryReply> findAllByDiaryWithPaging_QueryDsl(Long diaryId, Pageable pageable) {
        List<DiaryReply> foundReply = query.select(diaryReply)
                .from(diaryReply)
                .leftJoin(diaryReply.member, member)
                .fetchJoin()
                .where(diaryReply.diary.id.eq(diaryId))
                .orderBy(diaryReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (foundReply.size() > pageable.getPageSize()) {
            foundReply.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(foundReply, pageable, hasNext);
    }

    @Override
    public Long getReplyCount_QueryDsl(Long diaryId) {
        return query.select(diaryReply.count())
                .from(diaryReply)
                .where(diaryReply.diary.id.eq(diaryId))
                .fetchOne();
    }

    @Override
    public void deleteByDiaryId(Long diaryId) {
        query.delete(diaryReply)
                .where(diaryReply.diary.id.eq(diaryId))
                .execute();
    }
}
