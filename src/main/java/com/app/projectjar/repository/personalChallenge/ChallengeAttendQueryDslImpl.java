package com.app.projectjar.repository.personalChallenge;


import com.app.projectjar.entity.challenge.QChallenge;
import com.app.projectjar.entity.file.challenge.QChallengeFile;
import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import com.app.projectjar.entity.personalChallenge.QChallengeAttend;
import com.app.projectjar.entity.personalChallenge.QPersonalChallenge;
import com.app.projectjar.type.ChallengeAttendType;
import com.app.projectjar.type.ChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.file.challenge.QChallengeFile.challengeFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallengeAttend.groupChallengeAttend;
import static com.app.projectjar.entity.personalChallenge.QChallengeAttend.challengeAttend;
import static com.app.projectjar.entity.personalChallenge.QPersonalChallenge.personalChallenge;

@RequiredArgsConstructor
public class ChallengeAttendQueryDslImpl implements ChallengeAttendQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Integer getAttendCountByPersonalChallengeId_QueryDsl(Long personalChallengeId) {
        return query.select(challengeAttend.count())
                .from(challengeAttend)
                .where(challengeAttend.personalChallenge.id.eq(personalChallengeId))
                .fetchOne().intValue();
    }

    @Override
    public Long findByChallengeIdAndMemberId_QueryDsl(Long personalChallengeId, Long memberId) {
        return query.select(challengeAttend.count())
                .from(challengeAttend)
                .where(challengeAttend.personalChallenge.id.eq(personalChallengeId).and(challengeAttend.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public void deleteByPersonalChallengeIdAndMemberId_QueryDsl(Long personalChallengeId, Long memberId) {
        query.delete(challengeAttend)
                .where(challengeAttend.personalChallenge.id.eq(personalChallengeId).and(challengeAttend.member.id.eq(memberId)))
                .execute();
    }

    @Override
    public ChallengeAttend findPersonalChallengeAttendByPersonalChallengeIdAndMemberId_QueryDsl(Long personalChallengeId, Long memberId) {
        return query.select(challengeAttend)
                .from(challengeAttend)
                .where(challengeAttend.personalChallenge.id.eq(personalChallengeId).and(challengeAttend.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public Integer getCountByMemberId_QueryDsl(Long memberId) {
        return query.select(challengeAttend.count())
                .from(challengeAttend)
                .where(challengeAttend.challengeAttendStatus.eq(ChallengeAttendType.PARTICIPATION))
                .where(challengeAttend.member.id.eq(memberId))
                .fetchOne().intValue();
    }

    //    개인 챌린지 목록(페이징, 진행중)
    @Override
    public Page<ChallengeAttend> findAllWithPageAndChallenges_QueryDsl(String challengeStatus, Long memberId, Pageable pageable) {
        List<ChallengeAttend> foundChallengeAttend = query.select(challengeAttend)
                .from(challengeAttend)
                .where(challengeAttend.member.id.eq(memberId)
                        .and(challengeStatus.equals("NONPARTICIPANT") ? challengeAttend.challengeAttendStatus.eq(ChallengeAttendType.NONPARTICIPANT) : challengeAttend.challengeAttendStatus.eq(ChallengeAttendType.PARTICIPATION)))
                .leftJoin(challengeAttend.personalChallenge, personalChallenge)
                .fetchJoin()
                .orderBy(challengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challengeAttend.count())
                .from(challengeAttend)
                .where(challengeAttend.member.id.eq(memberId).and(challengeStatus.equals("NONPARTICIPANT") ? challengeAttend.challengeAttendStatus.eq(ChallengeAttendType.NONPARTICIPANT) : challengeAttend.challengeAttendStatus.eq(ChallengeAttendType.PARTICIPATION)))
                .fetchOne();

        return new PageImpl<>(foundChallengeAttend, pageable, count);
    }

    //    내가 완료한 개인 챌린지 전체 조회 (종료된 챌린지)
    @Override
    public Page<ChallengeAttend> findAllWithPageAndEndChallenges_QueryDsl(Long memberId, Pageable pageable) {
//        List<ChallengeAttend> foundChallengeAttend = query.selectFrom(challengeAttend)
//                .join(challengeAttend.member, member)
//                .leftJoin(challengeAttend.personalChallenge, personalChallenge)
//                .where(challengeAttend.member.id.eq(memberId))
//                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("PRIVATE")))
//                .leftJoin(challenge.challengeFiles, QChallengeFile.challengeFile)
//                .orderBy(challengeAttend.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = query.select(challengeAttend.count())
//                .from(challengeAttend)
//                .join(challengeAttend.member, member)
//                .where(member.id.eq(memberId))
//                .fetchOne();

//        return new PageImpl<>(foundChallengeAttend, pageable, count);
        return null;
    }

    //    개인 챌린지 목록(댓글 갯수)
    @Override
    public Long getChallengeReplyCount_QueryDsl(Long challengeId) {
//        QChallengeReply challengeReply = QChallengeReply.challengeReply;
//
//        return query.select(challengeReply.count())
//                .from(challengeReply)
//                .where(challengeReply.personalChallenge.id.eq(challengeId))
//                .fetchOne();
        return null;
    }

    @Override
    @Transactional
    public void deleteByChallengeId(Long challengeId) {
        query.delete(challengeAttend)
                .where(challengeAttend.personalChallenge.id.eq(challengeId))
                .execute();
    }


}
