package com.app.projectjar.repository.file.groupChallenge;

import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChallengeFileRepository extends JpaRepository<GroupChallengeFile, Long>, GroupChallengeFileQueryDsl {
}
