package com.app.projectjar.repository.file.challenge;

import com.app.projectjar.entity.file.challenge.QChallengeFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.app.projectjar.entity.file.challenge.QChallengeFile.challengeFile;
import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;

@RequiredArgsConstructor
public class ChallengeFileQueryDslImpl implements ChallengeFileQueryDsl {
    private final JPAQueryFactory query;


    @Override
    @Transactional
    public void deleteByChallengeId(Long challengeId) {
        query.delete(challengeFile)
                .where(challengeFile.challenge.id.eq(challengeId))
                .execute();
    }
}
