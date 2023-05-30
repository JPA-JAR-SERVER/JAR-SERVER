package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonalChallengeQueryDsl {

    public Page<PersonalChallenge> findAllByChallengeStatus(String challengeStatus, Pageable pageable);

    // 어제 insert된 목록 가져오기
    public List<PersonalChallenge> findByCreateDateYesterday(LocalDate createDate);

    public Optional<PersonalChallenge> findByPersonalChallengeId(Long personalChallengeId);

    // 전체 조회
    public Page<PersonalChallenge> findAllWithPaging_QueryDSL(Pageable pageable);
}
