package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.MemberRandomKey;
import com.app.projectjar.entity.member.QMemberRandomKey;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.member.QMemberRandomKey.memberRandomKey1;

@RequiredArgsConstructor
public class MemberRandomKeyQueryDslImpl implements MemberRandomKeyQueryDsl {
    private final JPAQueryFactory query;

    /* 회원의 가장 최근 랜덤키 찾기 */
    @Override
    public MemberRandomKey getListRandomKey(Long id) {
        return query.select(memberRandomKey1)
                .from(member)
                .join(member.memberRandomKeys, memberRandomKey1)
                .where(member.id.eq(id))
                .orderBy(memberRandomKey1.id.desc())
                .fetchFirst();
    }

}
