package com.app.projectjar.entity.challenge;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.challenge.ChallengeFile;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Challenge extends Board {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "challenge")
    private List<ChallengeFile> challengeFiles;

    @Builder
    public Challenge(Long id, String boardTitle, String boardContent, List<ChallengeFile> challengeFiles, LocalDateTime createDate) {
        super(id, boardTitle, boardContent);
        this.challengeFiles = challengeFiles;
        this.setCreatedDate(createDate);
    }

}
