package com.app.projectjar.repository.file.member;

import com.app.projectjar.entity.file.member.QMemberFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;

@RequiredArgsConstructor
public class MemberFileQueryDslImpl implements MemberFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public void deleteByMemberId(Long memberId) {
        query.delete(memberFile)
                .where(memberFile.member.id.eq(memberId))
                .execute();
    }
}
