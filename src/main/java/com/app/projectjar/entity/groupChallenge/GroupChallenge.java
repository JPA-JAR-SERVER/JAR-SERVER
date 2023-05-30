package com.app.projectjar.entity.groupChallenge;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BoardType;
import com.app.projectjar.type.GroupChallengeType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_GROUP_CHALLENGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class GroupChallenge extends Board {
    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'WAIT'")
    private GroupChallengeType groupChallengeStatus;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @Column(columnDefinition = "integer default 0")
    private Integer groupChallengeReplyCount;

    @Column(columnDefinition = "integer default 0")
    private Integer groupChallengeAttendCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupChallenge")
    private List<GroupChallengeFile> groupChallengeFiles;

    public void setGroupChallengeReplyCount(Integer groupChallengeReplyCount) {
        this.groupChallengeReplyCount = groupChallengeReplyCount;
    }

    public void setGroupChallengeAttendCount(Integer groupChallengeAttendCount) {
        this.groupChallengeAttendCount = groupChallengeAttendCount;
    }

    public void setGroupChallengeStatus(GroupChallengeType groupChallengeStatus) {
        this.groupChallengeStatus = groupChallengeStatus;
    }

    @Builder
    public GroupChallenge(Long id, String boardTitle, String boardContent, GroupChallengeType groupChallengeStatus, LocalDate startDate, LocalDate endDate, Integer groupChallengeReplyCount, Integer groupChallengeAttendCount, List<GroupChallengeFile> groupChallengeFiles, LocalDateTime createDate) {
        super(id, boardTitle, boardContent);
        this.groupChallengeStatus = groupChallengeStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupChallengeReplyCount = groupChallengeReplyCount;
        this.groupChallengeAttendCount = groupChallengeAttendCount;
        this.groupChallengeFiles = groupChallengeFiles;
        this.setCreatedDate(createDate);
    }


}
