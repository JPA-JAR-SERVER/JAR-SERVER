package com.app.projectjar.repository.notice;


import com.app.projectjar.entity.notice.Notice;
import com.app.projectjar.entity.notice.QNotice;
import com.app.projectjar.entity.suggest.Suggest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.notice.QNotice.notice;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class NoticeQueryDslImpl implements NoticeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Notice> findAllWithPaging_QueryDSL(Pageable pageable) {
        List<Notice> foundNotice = query.select(notice)
                .from(notice)
                .orderBy(notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(notice.count())
                .from(notice)
                .fetchOne();

        return new PageImpl<>(foundNotice, pageable, count);
    }
}
