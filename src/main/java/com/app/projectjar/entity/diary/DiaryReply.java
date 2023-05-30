package com.app.projectjar.entity.diary;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.reply.Reply;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_DIARY_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryReply extends Reply {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;


    @Builder
    public DiaryReply(Long id, String replyContent, Diary diary, Member member) {
        super(id, replyContent);
        this.diary = diary;
        this.member = member;
    }

    public void setDiaryReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }
}
