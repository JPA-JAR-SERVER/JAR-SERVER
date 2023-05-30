package com.app.projectjar.entity.diary;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.diary.DiaryFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.DiaryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = "member")
@Table(name ="TBL_DIARY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Diary extends Board {

    @Column(columnDefinition = "integer default 0")
    private Integer diaryLikeCount;

    @Column(columnDefinition = "integer default 0")
    private Integer diaryReplyCount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PRIVATE'")
    private DiaryType diaryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "diary")
    private List<DiaryFile> diaryFiles;

    public void setDiaryReplyCount(Integer diaryReplyCount) {
        this.diaryReplyCount = diaryReplyCount;
    }

    public void setDiaryLikeCount(Integer diaryLikeCount) {
        this.diaryLikeCount = diaryLikeCount;
    }

    @Builder
    public Diary(Long id, String boardTitle, String boardContent, Integer diaryLikeCount, Integer diaryReplyCount, DiaryType diaryStatus, Member member, List<DiaryFile> diaryFiles, LocalDateTime createDate) {
        super(id, boardTitle, boardContent);
        this.diaryLikeCount = diaryLikeCount;
        this.diaryReplyCount = diaryReplyCount;
        this.diaryStatus = diaryStatus;
        this.member = member;
        this.diaryFiles = diaryFiles;
        this.setCreatedDate(createDate);
    }
}
