package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.entity.groupChallenge.GroupChallengeReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.projectjar.entity.groupChallenge.QGroupChallengeAttend.groupChallengeAttend;
import static com.app.projectjar.entity.groupChallenge.QGroupChallengeReply.groupChallengeReply;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class GroupChallengeReplyQueryDslImpl implements GroupChallengeReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<GroupChallengeReply> findAllBySuggestWithPaging_QueryDsl(Long groupChallengeId, Pageable pageable) {
        List<GroupChallengeReply> foundReply = query.select(groupChallengeReply)
                .from(groupChallengeReply)
                .leftJoin(groupChallengeReply.member, member)
                .fetchJoin()
                .where(groupChallengeReply.groupChallenge.id.eq(groupChallengeId))
                .orderBy(groupChallengeReply.id.desc())
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
    public Long getReplyCount_QueryDsl(Long groupChallengeId) {
        return query.select(groupChallengeReply.count())
                .from(groupChallengeReply)
                .where(groupChallengeReply.groupChallenge.id.eq(groupChallengeId))
                .fetchOne();
    }

    @Override
    public void deleteBySuggestId(Long groupChallengeId) {
        query.delete(groupChallengeReply)
                .where(groupChallengeReply.groupChallenge.id.eq(groupChallengeId))
                .execute();
    }

    @Override
    @Transactional
    public void deleteByGroupChallengeId(Long groupChallengeId) {
        query.delete(groupChallengeReply)
                .where(groupChallengeReply.groupChallenge.id.eq(groupChallengeId))
                .execute();
    }
}
