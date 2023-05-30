package com.app.projectjar.entity.suggest;

import com.app.projectjar.entity.like.Likes;
import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_SUGGEST_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestLike extends Likes {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUGGEST_ID")
    private Suggest suggest;

    @Builder
    public SuggestLike(Member member, Suggest suggest) {
        this.member = member;
        this.suggest = suggest;
    }

    @Builder
    public SuggestLike(Long id, Member member, Suggest suggest) {
        super(id);
        this.member = member;
        this.suggest = suggest;
    }
}
