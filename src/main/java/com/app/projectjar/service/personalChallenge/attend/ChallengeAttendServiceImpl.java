package com.app.projectjar.service.personalChallenge.attend;

import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import com.app.projectjar.repository.groupChallenge.GroupChallengeAttendRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.personalChallenge.ChallengeAttendRepository;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.ChallengeAttendType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Qualifier("challengeAttend") @Primary
public class ChallengeAttendServiceImpl implements ChallengeAttendService {

    private final ChallengeAttendRepository challengeAttendRepository;

    private final MemberRepository memberRepository;

    private final PersonalChallengeRepository personalChallengeRepository;

    private final GroupChallengeAttendRepository groupChallengeAttendRepository;

    @Override @Transactional
    public void insertAttend(Long boardId, Long memberId) {
        memberRepository.findById(memberId).ifPresent(
                member ->
                        personalChallengeRepository.findById(boardId).ifPresent(
                                personalChallenge  -> {
                                    ChallengeAttend challengeAttend = ChallengeAttend.builder()
                                            .personalChallenge(personalChallenge)
                                            .member(member)
                                            .build();
                                    challengeAttendRepository.save(challengeAttend);
                                    personalChallenge.setChallengeAttendCount(getAttendCount(personalChallenge.getId()));
                                    personalChallengeRepository.save(personalChallenge);
                                }
                        )
        );
    }

    @Override @Transactional
    public void deleteAttend(Long boardId, Long memberId) {
        memberRepository.findById(memberId).ifPresent(
                member ->
                        personalChallengeRepository.findById(boardId).ifPresent(
                                personalChallenge -> {
                                    challengeAttendRepository.deleteByPersonalChallengeIdAndMemberId_QueryDsl(personalChallenge.getId(), member.getId());
                                    personalChallenge.setChallengeAttendCount(getAttendCount(personalChallenge.getId()));
                                    personalChallengeRepository.save(personalChallenge);
                                }
                        )
        );
    }

    @Override
    public Boolean attendCheck(Long boardId, Long memberId) {
        return challengeAttendRepository.findByChallengeIdAndMemberId_QueryDsl(boardId,memberId) == 0;
    }

    @Override
    public Boolean challengeSuccessCheck(Long boardId, Long memberId) {
        ChallengeAttend challengeAttend = challengeAttendRepository.findPersonalChallengeAttendByPersonalChallengeIdAndMemberId_QueryDsl(boardId, memberId);
        return challengeAttend.getChallengeAttendStatus().equals(ChallengeAttendType.PARTICIPATION);
    }

    @Override
    public void updateAttendToAttendType(Long boardId, Long memberId) {
        ChallengeAttend challengeAttend = challengeAttendRepository.findPersonalChallengeAttendByPersonalChallengeIdAndMemberId_QueryDsl(boardId, memberId);
        challengeAttend.setChallengeAttendStatus(ChallengeAttendType.PARTICIPATION);
        challengeAttendRepository.save(challengeAttend);

        int count = getAllChallengeAttendCount(memberId);

        updateBadgeType(memberId, count);
    }

    @Override
    public Integer getAttendCount(Long boardId) {
        return challengeAttendRepository.getAttendCountByPersonalChallengeId_QueryDsl(boardId);
    }

    private void updateBadgeType(Long memberId, int count){

        memberRepository.findById(memberId).ifPresent(
                member ->  {
                    if(count == 10){
                        member.setBadgeType(BadgeType.ONE);
                    }else if(count == 20){
                        member.setBadgeType(BadgeType.TWO);

                    }else if(count == 30){
                        member.setBadgeType(BadgeType.THREE);
                    }
                    memberRepository.save(member);
                }
        );
    }

    private int getAllChallengeAttendCount(Long memberId) {
        int groupChallengeAttendCount = groupChallengeAttendRepository.getCountByMemberId_QueryDsl(memberId);
        int challengeAttendCount = challengeAttendRepository.getCountByMemberId_QueryDsl(memberId);
        return groupChallengeAttendCount + challengeAttendCount;
    }
}
