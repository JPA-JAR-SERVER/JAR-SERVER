package com.app.projectjar.repository.challenge;


import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.challenge.QChallenge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.file.challenge.QChallengeFile.challengeFile;

@RequiredArgsConstructor
public class ChallengeQueryDslImpl implements ChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<Challenge> findAll_QueryDsl() {
        return query.select(challenge)
                .from(challenge)
                .distinct()
                .leftJoin(challenge.challengeFiles, challengeFile)
                .fetchJoin()
                .fetch();
    }


    @Override
    public Page<Challenge> findAllWithPaging_QueryDSL(Pageable pageable) {
        List<Challenge> foundChallenge = query.select(challenge)
                .from(challenge)
                .orderBy(challenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challenge.count())
                .from(challenge)
                .fetchOne();

        return new PageImpl<>(foundChallenge, pageable, count);
    }

    @Override
    public Challenge getCurrentSequence_QueryDsl() {
        return query.select(challenge)
                .from(challenge)
                .orderBy(challenge.id.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public Optional<Challenge> findByChallengeId_QueryDsl(Long challengeId) {
        Challenge challenge = query.select(QChallenge.challenge)
                .from(QChallenge.challenge)
                .leftJoin(QChallenge.challenge.challengeFiles, challengeFile)
                .fetchJoin()
                .where(QChallenge.challenge.id.eq(challengeId))
                .fetchOne();

        return Optional.ofNullable(challenge);
    }
}
