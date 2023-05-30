package com.app.projectjar.repository.file.groupChallenge;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GroupChallengeFileQueryDsl {

    // 그룹 챌린지 파일 하나 조회 ( 게시판 리스트에서 사용할 거)
    public Optional<GroupChallengeFile> findTop1ByGroupChallengeFileId_QueryDsl(Long groupChallengeId);

    // 그룹 챌린지 파일 여러개 조회 ( 게시판 상세보기에서 사용 )
//    public List<GroupChallengeFile> findAllByGroupChallengeFileId_QueryDsl(Long groupChallengeId);

    public List<FileDTO> findAllFiles(LocalDateTime date);

    public void deleteByGroupChallengeId(Long groupChallengeId);

}
