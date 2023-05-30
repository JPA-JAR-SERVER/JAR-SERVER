package com.app.projectjar.repository.file.diary;

import com.app.projectjar.entity.file.diary.QDiaryFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.projectjar.entity.file.diary.QDiaryFile.diaryFile;

@RequiredArgsConstructor
public class DiaryFileQueryDslImpl implements DiaryFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public void deleteByDiaryId(Long diaryId) {
        query.delete(diaryFile)
                .where(diaryFile.diary.id.eq(diaryId))
                .execute();
    }
}
