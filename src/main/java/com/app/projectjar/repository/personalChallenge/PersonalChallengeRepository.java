package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChallengeRepository extends JpaRepository<PersonalChallenge, Long>, PersonalChallengeQueryDsl {
}
