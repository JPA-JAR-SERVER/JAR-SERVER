package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.search.board.GroupChallengeSearch;
import com.app.projectjar.search.board.SuggestSearch;
import com.app.projectjar.type.BoardType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;


@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Suggest> findByBoardTypeWithPaging_QueryDsl(String boardType, Pageable pageable) {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .where(boardType.equals("personal") ? suggest.boardType.eq(BoardType.PERSONAL) : suggest.boardType.eq(BoardType.GROUP))
                .orderBy(suggest.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count()).from(suggest).where(boardType.equals("personal") ? suggest.boardType.eq(BoardType.PERSONAL) : suggest.boardType.eq(BoardType.GROUP)).fetchOne();

        return new PageImpl<>(foundSuggests, pageable, count);
    }

    @Override
    public Optional<Suggest> findByIdSuggest_QueryDsl(Long suggestId) {
        Suggest foundSuggest = query.select(suggest)
                .from(QSuggest.suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .leftJoin(suggest.member, member)
                .fetchJoin()
                .where(suggest.id.eq(suggestId))
                .fetchOne();

        return Optional.ofNullable(foundSuggest);
    }

    @Override
    public Suggest getCurrentSequence_QueryDsl() {
        return query.select(suggest)
                .from(suggest)
                .orderBy(suggest.id.desc())
                .limit(1)
                .fetchOne();
    }


    @Override
    public Page<Suggest> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Suggest> foundSuggest = query.select(suggest)
                .from(suggest)
                .where(suggest.member.id.eq(id))
                .leftJoin(suggest.suggestFiles)
                .fetchJoin()
                .orderBy(suggest.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count())
                .from(suggest)
                .where(suggest.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundSuggest, pageable, count);
    }

    /*관리자 페이지*/
    @Override
    public Page<Suggest> findAllWithPaging_QueryDsl(Pageable pageable) {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .orderBy(suggest.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count()).from(suggest).fetchOne();

        return new PageImpl<>(foundSuggests, pageable, count);
    }
/*메인 페이지*/
    // 페이징 없는 전체 조회
    @Override
    public List<Suggest> findAllWithoutPaging_QueryDsl() {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .distinct()
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .orderBy(suggest.id.desc())
                .fetch();

        return foundSuggests;
    }

    /*검색*/
    @Override
    public List<Suggest> findSuggestWithSearch_QueryDSL(String search) {
        BooleanExpression suggestTitleEq = search == null ? null : suggest.boardTitle.like("%" + search + "%");

        List<Suggest> suggestSearchs = query.select(suggest)
                .from(suggest)
                .where(suggestTitleEq)
                .distinct()
                .limit(3)
                .leftJoin(suggest.suggestFiles)
                .orderBy(suggest.id.desc())
                .fetch();

        return suggestSearchs;
    }
}
