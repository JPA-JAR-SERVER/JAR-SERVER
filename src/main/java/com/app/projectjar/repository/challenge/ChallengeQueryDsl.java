package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.challenge.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeQueryDsl {

    public List<Challenge> findAll_QueryDsl();

    // 전체 조회
    public Page<Challenge> findAllWithPaging_QueryDSL(Pageable pageable);

    // 현재 시퀀스 가져오기
    public Challenge getCurrentSequence_QueryDsl();

    // 상세 보기
    public Optional<Challenge> findByChallengeId_QueryDsl(Long challengeId);
}
