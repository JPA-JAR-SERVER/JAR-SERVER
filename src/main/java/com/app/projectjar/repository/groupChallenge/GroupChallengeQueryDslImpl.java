package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.groupChallenge.QGroupChallenge;
import com.app.projectjar.search.board.GroupChallengeSearch;
import com.app.projectjar.type.GroupChallengeType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;

@RequiredArgsConstructor
public class GroupChallengeQueryDslImpl implements GroupChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<GroupChallenge> findAllGroupChallengeWithPaging_QueryDsl(Pageable pageable) {
        List<GroupChallenge> foundGroupChallenge = query.select(groupChallenge)
                .from(groupChallenge)
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .orderBy(groupChallenge.id.desc())
                .where(groupChallenge.groupChallengeStatus.eq(GroupChallengeType.valueOf("OPEN")))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(groupChallenge.count())
                .from(groupChallenge)
                .where(groupChallenge.groupChallengeStatus.eq(GroupChallengeType.valueOf("OPEN")))
                .fetchOne();

        return new PageImpl<>(foundGroupChallenge, pageable, count);
    }

    @Override
    public Page<GroupChallenge> findAllWithPaging_QueryDSL(Pageable pageable) {
        List<GroupChallenge> foundGroupChallenge = query.select(groupChallenge)
                .from(groupChallenge)
                .orderBy(groupChallenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(groupChallenge.count())
                .from(groupChallenge)
                .fetchOne();

        return new PageImpl<>(foundGroupChallenge, pageable, count);
    }

    @Override
    public List<GroupChallenge> findAllCalendar() {
        return query.select(groupChallenge)
                .from(groupChallenge)
                .distinct()
                .fetch();
    }

    @Override
    public Page<GroupChallenge> findAllGroupChallengeByPrivateWithPaging_QueryDsl(Pageable pageable) {
        List<GroupChallenge> foundGroupChallenge = query.select(groupChallenge)
                .from(groupChallenge)
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .orderBy(groupChallenge.id.desc())
                .where(groupChallenge.groupChallengeStatus.eq(GroupChallengeType.valueOf("PRIVATE")))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(groupChallenge.count())
                .from(groupChallenge)
                .where(groupChallenge.groupChallengeStatus.eq(GroupChallengeType.valueOf("PRIVATE")))
                .fetchOne();

        return new PageImpl<>(foundGroupChallenge, pageable, count);
    }

    @Override
    public Optional<GroupChallenge> findByGroupChallengeId_QueryDsl(Long groupChallengeId) {
        GroupChallenge groupChallenge = query.select(QGroupChallenge.groupChallenge)
                .from(QGroupChallenge.groupChallenge)
                .leftJoin(QGroupChallenge.groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .where(QGroupChallenge.groupChallenge.id.eq(groupChallengeId))
                .fetchOne();

        return Optional.ofNullable(groupChallenge);
    }

    @Override
    public GroupChallenge getCurrentSequence_QueryDsl() {
        return query.select(groupChallenge)
                .from(groupChallenge)
                .orderBy(groupChallenge.id.desc())
                .limit(1)
                .fetchOne();
    }

    // 목록(페이징 처리 없는 버전)
    @Override
    public List<GroupChallenge> findAllGroupChallenge_QueryDsl() {
        return query.select(groupChallenge)
                .from(groupChallenge)
                .distinct()
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .orderBy(groupChallenge.id.desc())
                .fetch();
    }

    /*검색*/
    @Override
    public List<GroupChallenge> findGroupChallengeWithSearch_QueryDSL(String search) {
        BooleanExpression groupChallengeTitleEq = search == null ? null : groupChallenge.boardTitle.like("%" + search +"%");

        List<GroupChallenge> groupChallengeSearchs = query.select(groupChallenge)
                .from(groupChallenge)
                .where(groupChallengeTitleEq)
                .distinct()
                .limit(3)
                .leftJoin(groupChallenge.groupChallengeFiles)
                .orderBy(groupChallenge.id.desc())
                .fetch();

        return groupChallengeSearchs;
    }

    @Override
    public List<GroupChallenge> findByStartDate(LocalDate startDate) {
        return query.select(groupChallenge)
                .from(groupChallenge)
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .where(groupChallenge.startDate.eq(startDate))
                .fetch();
    }

    @Override
    public List<GroupChallenge> findByEndDate(LocalDate endDate) {
        return query.select(groupChallenge)
                .from(groupChallenge)
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .where(groupChallenge.endDate.eq(endDate))
                .fetch();
    }

}
