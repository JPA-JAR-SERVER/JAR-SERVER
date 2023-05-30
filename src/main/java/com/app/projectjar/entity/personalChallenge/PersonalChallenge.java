package com.app.projectjar.entity.personalChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.audit.TimeDate;
import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.type.ChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_PERSONAL_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class PersonalChallenge extends TimeDate {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'OPEN'")
    private ChallengeType challengeStatus;
    @Column(columnDefinition = "integer default 0")
    private Integer challengeReplyCount;
    @Column(columnDefinition = "integer default 0")
    private Integer challengeAttendCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    @Builder
    public PersonalChallenge(Long id, ChallengeType challengeStatus, Integer challengeReplyCount, Integer challengeAttendCount, Challenge challenge) {
        this.id = id;
        this.challengeStatus = challengeStatus;
        this.challengeReplyCount = challengeReplyCount;
        this.challengeAttendCount = challengeAttendCount;
        this.challenge = challenge;
    }




    public void setChallengeStatus(ChallengeType challengeStatus) {
        this.challengeStatus = challengeStatus;
    }

    public void setChallengeReplyCount(Integer challengeReplyCount) {
        this.challengeReplyCount = challengeReplyCount;
    }

    public void setChallengeAttendCount(Integer challengeAttendCount) {
        this.challengeAttendCount = challengeAttendCount;
    }
}
