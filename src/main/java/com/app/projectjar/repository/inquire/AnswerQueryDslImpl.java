package com.app.projectjar.repository.inquire;


import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.inquire.QAnswer;
import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.inquire.QAnswer.answer;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class AnswerQueryDslImpl implements AnswerQueryDsl {
    private final JPAQueryFactory query;

//    @Override
//    public Slice<Answer> findAllByAnswerWithPaging_QueryDsl(Long id, Pageable pageable) {
//        List<Answer> foundAnswer = query.select(answer)
//                .from(answer)
////                .leftJoin(answer., )
//                .fetchJoin()
//                .where(answer.inquire.id.eq(id))
//                .orderBy(answer.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1)
//                .fetch();
//
//    }

    @Override
    public Optional<Answer> findByIdAnswer_QueryDsl(Long answerId) {
        Answer foundAnswer = query.select(answer)
                .from(answer)
                .fetchJoin()
                .where(answer.id.eq(answerId))
                .fetchOne();

        return Optional.ofNullable(foundAnswer);
    }

    @Transactional
    @Override
    public void deleteByAnswerId(Long answerId) {
        query.delete(answer)
                .where(answer.id.eq(answerId))
                .execute();
    }
}
