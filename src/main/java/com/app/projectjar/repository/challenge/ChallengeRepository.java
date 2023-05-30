package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>, ChallengeQueryDsl  {
}
