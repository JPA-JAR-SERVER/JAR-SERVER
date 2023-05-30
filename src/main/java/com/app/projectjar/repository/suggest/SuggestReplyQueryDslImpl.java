package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class SuggestReplyQueryDslImpl implements SuggestReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<SuggestReply> findAllBySuggestWithPaging_QueryDsl(Long suggestId, Pageable pageable) {
        List<SuggestReply> foundReply = query.select(suggestReply)
                .from(suggestReply)
                .leftJoin(suggestReply.member, member)
                .fetchJoin()
                .where(suggestReply.suggest.id.eq(suggestId))
                .orderBy(suggestReply.id.desc())
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
    public Long getReplyCount_QueryDsl(Long suggestId) {
        return query.select(suggestReply.count())
                .from(suggestReply)
                .where(suggestReply.suggest.id.eq(suggestId))
                .fetchOne();
    }

    @Override
    public void deleteBySuggestId(Long suggestId) {
        query.delete(suggestReply)
                .where(suggestReply.suggest.id.eq(suggestId))
                .execute();
    }
}
