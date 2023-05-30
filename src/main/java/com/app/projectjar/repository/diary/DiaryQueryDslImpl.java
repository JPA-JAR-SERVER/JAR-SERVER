package com.app.projectjar.repository.diary;


import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.type.DiaryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.diary.QDiary.diary;
import static com.app.projectjar.entity.diary.QDiaryLike.diaryLike;
import static com.app.projectjar.entity.file.diary.QDiaryFile.diaryFile;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class DiaryQueryDslImpl implements DiaryQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public Optional<Diary> findByDiaryId_QueryDsl(Long diaryId) {
        Diary foundDiary = query.select(diary)
                .from(diary)
                .leftJoin(diary.diaryFiles, diaryFile)
                .fetchJoin()
                .leftJoin(diary.member, member)
                .fetchJoin()
                .where(diary.id.eq(diaryId))
                .fetchOne();

        return Optional.ofNullable(foundDiary);
    }

    @Override
    public Page<Diary> findAllWithPaging_QueryDSL(Pageable pageable) {
        List<Diary> foundDiary = query.select(diary)
                .from(diary)
                .orderBy(diary.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(diary.count())
                .from(diary)
                .fetchOne();

        return new PageImpl<>(foundDiary, pageable, count);
    }

    @Override
    public Slice<Diary> findByMemberIdDiary_QueryDsl(String sort, Pageable pageable) {
            List<Diary> foundDiaryList = query.select(diary)
                    .from(diary)
                    .leftJoin(diary.diaryFiles, diaryFile)
                    .fetchJoin()
                    .orderBy(sort.equals("recent") ? diary.id.desc() : diary.diaryLikeCount.desc())
                    .where(diary.diaryStatus.eq(DiaryType.valueOf("OPEN")))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize() + 1)
                    .fetch();

        boolean hasNext = false;
        if (foundDiaryList.size() > pageable.getPageSize()) {
            foundDiaryList.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(foundDiaryList, pageable, hasNext);
    }

    @Override
    public List<Diary> findByMemberIdDiary_QueryDsl(Long memberId) {
        return query.select(diary)
                .distinct()
                .from(diary)
                .leftJoin(diary.diaryFiles, diaryFile)
                .fetchJoin()
                .where(diary.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public Page<Diary> findAllByMemberWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Diary> foundDiaries = query.select(diary)
                .from(diary)
                .where(diary.member.id.eq(id))
                .leftJoin(diary.diaryFiles)
                .fetchJoin()
                .orderBy(diary.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(diary.count())
                .from(diary)
                .where(diary.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundDiaries, pageable, count);
    }

    @Override
    public Page<Diary> findByLikeMEmberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Diary> foundDiaries = query.select(diary)
                .from(diary)
                .leftJoin(diaryLike)
                .where(diary.member.id.eq(id))
                .orderBy(diary.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(foundDiaries);
    }

    @Override
    public Diary getCurrentSequence_QueryDsl() {
        return query.select(diary)
                .from(diary)
                .orderBy(diary.id.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public void deleteByDiaryId_QueryDsl(Long diaryId) {
        query.delete(diary)
                .where(diary.id.eq(diaryId))
                .execute();
    }


}
