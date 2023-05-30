package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.ChallengeReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ChallengeReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Slice<ChallengeReply> findAllByChallengeWithPaging(Long personalChallengeId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount(Long personalChallengeId);

    // 댓글 삭제
    public void deleteByPersonalChallengeId(Long personalChallengeId);

    public void deleteByChallengeId(Long challengeId);
}
