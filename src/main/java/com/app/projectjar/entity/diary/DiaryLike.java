package com.app.projectjar.entity.diary;

import com.app.projectjar.entity.like.Likes;
import com.app.projectjar.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_DIARY_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryLike  extends Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public DiaryLike(Long id, Diary diary, Member member) {
        super(id);
        this.diary = diary;
        this.member = member;
    }
}
