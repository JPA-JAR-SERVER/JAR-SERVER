package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChallengeAttendRepository extends JpaRepository<GroupChallengeAttend, Long>, GroupChallengeAttendQueryDsl {
}
