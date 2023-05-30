package com.app.projectjar.entity.personalChallenge;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.type.ChallengeAttendType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE_ATTEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class ChallengeAttend extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'NONPARTICIPANT'")
    private ChallengeAttendType challengeAttendStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONAL_CHALLENGE_ID")
    private PersonalChallenge personalChallenge;

    @Builder
    public ChallengeAttend(Long id, ChallengeAttendType challengeAttendStatus, Member member, PersonalChallenge personalChallenge) {
        this.id = id;
        this.challengeAttendStatus = challengeAttendStatus;
        this.member = member;
        this.personalChallenge = personalChallenge;
    }

    public void setChallengeAttendStatus(ChallengeAttendType challengeAttendStatus) {
        this.challengeAttendStatus = challengeAttendStatus;
    }
}
