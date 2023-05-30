package com.app.projectjar.entity.file.groupChallenge;

import com.app.projectjar.entity.file.Files;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.type.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "groupChallenge")
@Table(name = "TBL_GROUP_CHALLENGE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallengeFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupChallenge groupChallenge;

    public GroupChallengeFile(String fileOriginalName, String fileUuid, String filePath, FileType fileType, GroupChallenge groupChallenge) {
        super(fileOriginalName, fileUuid, filePath, fileType);
        this.groupChallenge = groupChallenge;
    }

    @Builder
    public GroupChallengeFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, GroupChallenge groupChallenge) {
        super(id, fileOriginalName, fileUuid, filePath, fileType);
        this.groupChallenge = groupChallenge;
    }
}
