package com.app.projectjar.repository.file.suggest;

import com.app.projectjar.entity.file.suggest.QSuggestFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;

@RequiredArgsConstructor
public class SuggestFIleQueryDslImpl implements SuggestFIleQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public void deleteBySuggestId(Long suggestId) {
        query.delete(suggestFile)
                .where(suggestFile.suggest.id.eq(suggestId))
                .execute();
    }
}
