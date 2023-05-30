package com.app.projectjar.entity.personalChallenge;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_CHALLENGE_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeReply extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONAL_CHALLENGE_ID")
    private PersonalChallenge personalChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Builder
    public ChallengeReply(Long id, String replyContent, PersonalChallenge personalChallenge, Member member) {
        super(id, replyContent);
        this.personalChallenge = personalChallenge;
        this.member = member;
    }


    public void setChallengeReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }
}
