package com.app.projectjar.repository.member;

import com.app.projectjar.entity.groupChallenge.QGroupChallengeAttend;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.entity.member.QMemberRandomKey;
import com.app.projectjar.entity.personalChallenge.QChallengeAttend;
import com.app.projectjar.type.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallengeAttend.groupChallengeAttend;
import static com.app.projectjar.entity.member.QMember.*;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.member.QMemberRandomKey.memberRandomKey1;
import static com.app.projectjar.entity.personalChallenge.QChallengeAttend.challengeAttend;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public void withDraw_QueryDsl(Long memberId) {
        query.update(member)
                .set(member.memberStatus, MemberType.DISABLE)
                .where(member.id.eq(memberId))
                .execute();
    }

    @Override
    public Member findById_QueryDSL(Long id) {
        return query.select(member).from(member).where(member.id.eq(id)).fetchOne();
    }

    //    이메일 중복 검사
    @Override
    public Long overlapByMemberEmail_QueryDSL(String memberEmail) {
        return query.select(member.count()).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();
    }

//    휴대폰 중복 검사
    @Override
    public Long overlapByPhoneNumber_QueryDSL(String memberPhoneNumber) {
        return query.select(member.count()).from(member).where(member.memberPhoneNumber.eq(memberPhoneNumber)).fetchOne();
    }

//    닉네임 중복검사
    @Override
    public Long overlapByNickName_QueryDSL(String memberNickName) {
        return query.select(member.count()).from(member).where(member.memberNickname.eq(memberNickName)).fetchOne();
    }

    //    비밀 번호 찾기
    @Override
    public Optional<Member> findByMemberEmailForPassword_QueryDSL(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

//    비밀 번호 변경
    @Override
    public void updatePassword_QueryDSL(Long id, String memberPassword) {
        query.update(member).set(member.memberPassword, memberPassword).where(member.id.eq(id)).execute();
    }

//    멤버 정보 조회
    @Override
    public Optional<Member> findByMemberId_QueryDSL(Long id) {
        Member member = query.select(QMember.member)
                .from(QMember.member)
                .leftJoin(QMember.member.memberFile, memberFile)
                .fetchJoin()
                .where(QMember.member.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByMemberEmail_QueryDSL(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

    @Override
    public Member findByMemberEmailNoOptional_QueryDSL(String memberEmail) {
        return query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne();
    }

    /* 랜덤키로 회원 찾기 */
    public Member findMemberByRandomKey(String memberRandomKey){
        return query.select(member)
                .from(QMemberRandomKey.memberRandomKey1)
                .join(memberRandomKey1.member, member)
                .where(memberRandomKey1.memberRandomKey.eq(memberRandomKey))
                .fetchOne();
    }

    /* 랜덤키로 회원 찾기 */
    @Override
    public Member findMemberByMemberEmailAndRandomKey(String memberEmail, String memberRandomKey) {
        return query.select(member)
                .from(memberRandomKey1)
                .join(memberRandomKey1.member, member)
                .where(memberRandomKey1.memberRandomKey.eq(memberRandomKey),memberRandomKey1.member.memberEmail.eq(memberEmail))
                .fetchOne();
    }

    //    회원 정보 수정
    @Override
    public void updateMember_QueryDSL(Member memberInfo) {
                query.update(member).
                set(member.memberName, memberInfo.getMemberName()).
                set(member.memberNickname, memberInfo.getMemberNickname()).
                set(member.memberPhoneNumber, memberInfo.getMemberPhoneNumber()).
                where(member.eq(memberInfo)).execute();
    }

    //    회원 삭제
    @Override
    public void deleteMemberById_QueryDSL(Long id) {
            query.delete(member).where(member.id.eq(id)).execute();

    }

//    챌린지 횟수 조회
    @Override
    public int findByIdWithAttendCount_QueryDsl(Long id) {
        Long countPersonal = query.select(challengeAttend.member.count())
                .from(challengeAttend).
                where(challengeAttend.member.id.eq(id).and(challengeAttend.challengeAttendStatus.eq(ChallengeAttendType.PARTICIPATION))).
                fetchOne();

        Long countGroup = query.select(groupChallengeAttend.member.count()).
                from(groupChallengeAttend).
                where(groupChallengeAttend.member.id.eq(id).and(groupChallengeAttend.groupChallengeAttendStatus.eq(GroupChallengeAttendType.PARTICIPATION))).
                fetchOne();

        int totalCount = countPersonal.intValue() + countGroup.intValue();

        return totalCount;


    }





    //    뱃지 수정
    @Override
    public void updateMemberBadge_QueryDSL(Long id) {

        Long countPersonal =  query.select(challengeAttend.member.count()).
                from(challengeAttend).
                where(challengeAttend.member.id.eq(id)).
                fetchOne();

        Long countGroup = query.select(groupChallengeAttend.member.count()).
                from(groupChallengeAttend).
                where(groupChallengeAttend.member.id.eq(id)).
                fetchOne();

        int totalCount = countPersonal.intValue() + countGroup.intValue();

//        BadgeType newBadgeType = badgeType;
//
//        if (totalCount >= 10) {
//            newBadgeType = BadgeType.ONE;
//
//            if (totalCount >= 20) {
//                newBadgeType = BadgeType.TWO;
//            }
//            if (totalCount >= 30) {
//                newBadgeType = BadgeType.THREE;
//            }
//        } else {
//            newBadgeType = BadgeType.ZERO;
//        }
//        if (newBadgeType != badgeType) {

//        연산은 서비스에서 진행하기
        BadgeType badgeType = BadgeType.ZERO;
        query.update(member)
                    .set(member.badgeType, badgeType)
                    .where(member.id.eq(id))
                    .execute();
    }


    //------------------------------------------ 관리자 페이지 -----------------------------------------------------
    //    관리자 페이지 회원 전체 조회
    @Override
    public Page<Member> findAllByMemberId_QueryDsl(Pageable pageable) {
        List<Member> foundMembers = query.select(member)
                .from(member)
                .leftJoin(member.memberFile, memberFile)
                .fetchJoin()
                .where(member.memberType.eq(Role.MEMBER))
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count())
                .from(member)
                .fetchOne();

        return new PageImpl<>(foundMembers, pageable ,count);
    }

    //    회원 정보 수정
    @Override
    public void updateMemberAdmin_QueryDSL(Member memberInfo) {
        query.update(member).
                set(member.memberEmail, memberInfo.getMemberEmail()).
                set(member.memberNickname, memberInfo.getMemberNickname()).
                set(member.memberPhoneNumber, memberInfo.getMemberPhoneNumber()).
                set(member.memberStatus, memberInfo.getMemberStatus()).
                where(member.eq(memberInfo)).execute();
    }

    //    관리자 회원 상세보기 챌린지 갯수
    @Override
    public Long findPersonalAttendCountByMemberId(Long memberId) {
        QChallengeAttend qChallengeAttend = QChallengeAttend.challengeAttend;
        return query.select(qChallengeAttend.count())
                .from(qChallengeAttend)
                .where(qChallengeAttend.member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Long findGroupAttendCountByMemberId(Long memberId) {
        QGroupChallengeAttend qGroupChallengeAttend = QGroupChallengeAttend.groupChallengeAttend;
        return query.select(qGroupChallengeAttend.count())
                .from(qGroupChallengeAttend)
                .where(qGroupChallengeAttend.member.id.eq(memberId))
                .fetchOne();
    }

}
