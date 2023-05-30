package com.app.projectjar.repository.inquire;


import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.inquire.QInquire;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.projectjar.entity.inquire.QInquire.inquire;

@RequiredArgsConstructor
public class InquireQueryDslImpl implements InquireQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Inquire> findAllWithPaging_QueryDSL(Pageable pageable){
        List<Inquire> foundInquire = query.select(inquire)
                .from(inquire)
                .join(inquire.member)
                .fetchJoin()
                .orderBy(inquire.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(inquire.count())
                .from(inquire)
                .fetchOne();
        return new PageImpl<>(foundInquire, pageable, count);
    }

    @Override
    public Page<Inquire> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Inquire> foundInquire = query.select(inquire)
                .from(inquire)
                .where(inquire.member.id.eq(id))
                .orderBy(inquire.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(inquire.count())
                .from(inquire)
                .where(inquire.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundInquire, pageable, count);
    }

    @Transactional
    @Override
    public void deleteByInquireId(Long inquireId) {
        query.delete(inquire)
                .where(inquire.id.eq(inquireId))
                .execute();
    }

}
