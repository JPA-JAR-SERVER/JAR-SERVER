package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.ChallengeReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeReplyRepository extends JpaRepository<ChallengeReply, Long>, ChallengeReplyQueryDsl {
}
