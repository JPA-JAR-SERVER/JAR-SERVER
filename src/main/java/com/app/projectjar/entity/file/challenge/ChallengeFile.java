package com.app.projectjar.entity.file.challenge;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.file.Files;
import com.app.projectjar.type.FileType;
import jdk.jshell.Snippet;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter @ToString(callSuper = true, exclude = "challenge")
@Table(name = "TBL_CHALLENGE_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    public ChallengeFile(String fileOriginalName, String fileUuid, String filePath, FileType fileType, Challenge challenge) {
        super(fileOriginalName, fileUuid, filePath, fileType);
        this.challenge = challenge;
    }

    @Builder
    public ChallengeFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Challenge challenge) {
        super(id, fileOriginalName, fileUuid, filePath, fileType);
        this.challenge = challenge;
    }

}
