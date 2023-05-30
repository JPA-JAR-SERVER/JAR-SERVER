package com.app.projectjar.repository.personalChallenge;


import com.app.projectjar.entity.personalChallenge.ChallengeReply;
import com.app.projectjar.entity.personalChallenge.QChallengeReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.personalChallenge.QChallengeReply.challengeReply;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class ChallengeReplyQueryDslImpl implements ChallengeReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<ChallengeReply> findAllByChallengeWithPaging(Long personalChallengeId, Pageable pageable) {
        List<ChallengeReply> foundReply = query.select(challengeReply)
                .from(challengeReply)
                .leftJoin(challengeReply.member, member)
                .fetchJoin()
                .where(challengeReply.personalChallenge.id.eq(personalChallengeId))
                .orderBy(challengeReply.id.desc())
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
    public Long getReplyCount(Long personalChallengeId) {
        return query.select(challengeReply.count())
                .from(challengeReply)
                .where(challengeReply.personalChallenge.id.eq(personalChallengeId))
                .fetchOne();
    }

    @Override
    public void deleteByPersonalChallengeId(Long personalChallengeId) {
        query.delete(challengeReply)
                .where(challengeReply.personalChallenge.id.eq(personalChallengeId))
                .execute();
    }

    @Transactional
    @Override
    public void deleteByChallengeId(Long challengeId) {
        query.delete(challengeReply)
                .where(challengeReply.personalChallenge.id.eq(challengeId))
                .execute();
    }
}
