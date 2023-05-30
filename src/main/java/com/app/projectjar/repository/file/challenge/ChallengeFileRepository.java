package com.app.projectjar.repository.file.challenge;

import com.app.projectjar.entity.file.challenge.ChallengeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeFileRepository extends JpaRepository<ChallengeFile, Long> ,ChallengeFileQueryDsl {
}
