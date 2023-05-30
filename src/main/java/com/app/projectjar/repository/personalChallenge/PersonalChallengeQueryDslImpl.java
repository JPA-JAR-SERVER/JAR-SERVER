package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.entity.personalChallenge.QPersonalChallenge;
import com.app.projectjar.type.ChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;
import static com.app.projectjar.entity.personalChallenge.QPersonalChallenge.personalChallenge;

@RequiredArgsConstructor
public class PersonalChallengeQueryDslImpl implements PersonalChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<PersonalChallenge> findAllByChallengeStatus(String challengeStatus, Pageable pageable) {
        List<PersonalChallenge> personalChallengeList = query.select(personalChallenge)
                .from(personalChallenge)
                .leftJoin(personalChallenge.challenge, challenge)
                .fetchJoin()
                .where(challengeStatus.equals("OPEN") ? personalChallenge.challengeStatus.eq(ChallengeType.OPEN) : personalChallenge.challengeStatus.eq(ChallengeType.PRIVATE))
                .orderBy(personalChallenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(personalChallenge.count())
                            .from(personalChallenge)
                            .where(challengeStatus.equals("OPEN") ? personalChallenge.challengeStatus.eq(ChallengeType.OPEN) : personalChallenge.challengeStatus.eq(ChallengeType.PRIVATE))
                            .fetchOne();

        return new PageImpl<>(personalChallengeList, pageable, count);
    }

    @Override
    public List<PersonalChallenge> findByCreateDateYesterday(LocalDate createDate) {
        List<PersonalChallenge> personalChallengeList = query.select(personalChallenge)
                .from(personalChallenge)
                .leftJoin(personalChallenge.challenge, challenge)
                .fetchJoin()
                .where(personalChallenge.createdDate.eq(createDate))
                .fetch();

        return personalChallengeList;
    }

    @Override
    public Optional<PersonalChallenge> findByPersonalChallengeId(Long personalChallengeId) {
        PersonalChallenge personalChallenge = query.select(QPersonalChallenge.personalChallenge)
                .from(QPersonalChallenge.personalChallenge)
                .leftJoin(QPersonalChallenge.personalChallenge.challenge, challenge)
                .fetchJoin()
                .where(QPersonalChallenge.personalChallenge.id.eq(personalChallengeId))
                .fetchOne();

        return Optional.ofNullable(personalChallenge);
    }


    @Override
    public Page<PersonalChallenge> findAllWithPaging_QueryDSL(Pageable pageable) {
        List<PersonalChallenge> foundPersonalChallenge = query.select(personalChallenge)
                .from(personalChallenge)
                .orderBy(personalChallenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(personalChallenge.count())
                .from(personalChallenge)
                .fetchOne();

        return new PageImpl<>(foundPersonalChallenge, pageable, count);
    }
}
