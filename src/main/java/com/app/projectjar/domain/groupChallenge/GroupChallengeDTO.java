package com.app.projectjar.domain.groupChallenge;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.type.GroupChallengeAttendType;
import com.app.projectjar.type.GroupChallengeType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@ToString
@NoArgsConstructor
public class GroupChallengeDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private GroupChallengeType groupChallengeStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer groupChallengeReplyCount;
    private Integer attendCount;
    private Integer replyCount;
    private LocalDateTime createdDate;
    private GroupChallengeAttendType groupChallengeAttendStatus;


    private String requestStartDate;
    private String requestEndDate;

    private List<FileDTO> fileDTOS;

    @Builder
    public GroupChallengeDTO(Long id, String boardTitle, String boardContent, GroupChallengeType groupChallengeStatus, LocalDate startDate, LocalDate endDate, Integer groupChallengeReplyCount, Integer attendCount, Integer replyCount, LocalDateTime createdDate, GroupChallengeAttendType groupChallengeAttendStatus, String requestStartDate, String requestEndDate, List<FileDTO> fileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.groupChallengeStatus = groupChallengeStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupChallengeReplyCount = groupChallengeReplyCount;
        this.attendCount = attendCount;
        this.replyCount = replyCount;
        this.createdDate = createdDate;
        this.groupChallengeAttendStatus = groupChallengeAttendStatus;
        this.requestStartDate = requestStartDate;
        this.requestEndDate = requestEndDate;
        this.fileDTOS = fileDTOS;
    }
}
