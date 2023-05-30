package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChallengeAttendQueryDsl {

    // 챌린지 참여 인원 가져오기
    public Integer getAttendCountByPersonalChallengeId_QueryDsl(Long personalChallengeId);

    // 해당 챌린지에 참여햇는지 여부 검사
    public Long findByChallengeIdAndMemberId_QueryDsl(Long personalChallengeId, Long memberId);

    // 참여 취소
    public void deleteByPersonalChallengeIdAndMemberId_QueryDsl(Long personalChallengeId, Long memberId);

    // 챌린지 참여 번호 가져오기
    public ChallengeAttend findPersonalChallengeAttendByPersonalChallengeIdAndMemberId_QueryDsl(Long personalChallengeId, Long memberId);

    // 참여완료한 갯수 가져오기
    public Integer getCountByMemberId_QueryDsl(Long memberId);

    /* 개인 챌린지 */

    //    내가 완료한 개인 챌린지 전체 조회 (완료) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건 / 참여한 인원 수 + 댓글 개수) + 페이징 처리
    public Page<ChallengeAttend> findAllWithPageAndChallenges_QueryDsl(String challengeStatus, Long memberId, Pageable pageable);

    //    내가 완료한 개인 챌린지 전체 조회 (종료된 챌린지)
    public Page<ChallengeAttend> findAllWithPageAndEndChallenges_QueryDsl(Long memberId, Pageable pageable);

    public Long getChallengeReplyCount_QueryDsl(Long challengeId);

    public void deleteByChallengeId(Long challengeId);
}
