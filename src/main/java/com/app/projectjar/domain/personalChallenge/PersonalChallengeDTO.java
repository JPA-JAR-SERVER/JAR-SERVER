package com.app.projectjar.domain.personalChallenge;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import com.app.projectjar.type.ChallengeAttendType;
import com.app.projectjar.type.ChallengeType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class PersonalChallengeDTO {
    private Long id;
    private ChallengeType challengeStatus;
    private LocalDate startDate;
    private Integer replyCount;
    private Integer attendCount;

    private ChallengeAttendType challengeAttendStatus;

    private ChallengeDTO challengeDTO;


    @Builder
    public PersonalChallengeDTO(Long id, ChallengeType challengeStatus, LocalDate startDate, Integer replyCount, Integer attendCount, ChallengeAttendType challengeAttendStatus, ChallengeDTO challengeDTO) {
        this.id = id;
        this.challengeStatus = challengeStatus;
        this.startDate = startDate;
        this.replyCount = replyCount;
        this.attendCount = attendCount;
        this.challengeAttendStatus = challengeAttendStatus;
        this.challengeDTO = challengeDTO;
    }
}
