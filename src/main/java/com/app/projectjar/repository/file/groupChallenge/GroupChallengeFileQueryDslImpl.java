package com.app.projectjar.repository.file.groupChallenge;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.file.QFileDTO;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.QFiles.files;
import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;

@RequiredArgsConstructor
public class GroupChallengeFileQueryDslImpl implements GroupChallengeFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Optional<GroupChallengeFile> findTop1ByGroupChallengeFileId_QueryDsl(Long groupChallengeId) {
        GroupChallengeFile foundFile = query.select(groupChallengeFile)
                .from(groupChallengeFile)
                .where(groupChallengeFile.groupChallenge.id.eq(groupChallengeId))
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(foundFile);
    }

    @Override
    public List<FileDTO> findAllFiles(LocalDateTime date) {
        return query.select(new QFileDTO(files.id,files.fileOriginalName, files.fileUuid, files.filePath))
                .from(files)
                .where(files.filePath.eq(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))))
                .fetch();
    }

    @Override
    @Transactional
    public void deleteByGroupChallengeId(Long groupChallengeId) {
        query.delete(groupChallengeFile)
                .where(groupChallengeFile.groupChallenge.id.eq(groupChallengeId))
                .execute();
    }

}
