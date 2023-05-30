package com.app.projectjar.entity.suggest;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.reply.Reply;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true,exclude = "suggest")
@Table(name = "TBL_SUGGEST_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestReply extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUGGEST_ID")
    private Suggest suggest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public SuggestReply(String replyContent, Suggest suggest, Member member) {
        super(replyContent);
        this.suggest = suggest;
        this.member = member;
    }

    public void setSuggestReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }

    @Builder
    public SuggestReply(Long id, String replyContent, Suggest suggest, Member member) {
        super(id, replyContent);
        this.suggest = suggest;
        this.member = member;
    }
}
