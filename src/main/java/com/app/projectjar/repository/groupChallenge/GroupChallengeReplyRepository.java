package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallengeReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChallengeReplyRepository extends JpaRepository<GroupChallengeReply, Long>, GroupChallengeReplyQueryDsl {
}
