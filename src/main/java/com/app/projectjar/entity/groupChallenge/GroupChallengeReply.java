package com.app.projectjar.entity.groupChallenge;


import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.reply.Reply;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_GROUP_CHALLENGE_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupChallengeReply extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CHALLENGE_ID")
    private GroupChallenge groupChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    public void setGroupChallengeReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }

    @Builder
    public GroupChallengeReply(Long id, String replyContent, GroupChallenge groupChallenge, Member member) {
        super(id, replyContent);
        this.groupChallenge = groupChallenge;
        this.member = member;
    }
}
