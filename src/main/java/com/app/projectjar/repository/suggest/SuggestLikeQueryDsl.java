package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.SuggestLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SuggestLikeQueryDsl {

    public Long findMemberBySuggestLike_QueryDsl(Long suggestId, Long memberId);

    public Long getSuggestLikeCount_QueryDsl(Long suggestId);

    public void deleteByMemberIdAndSuggestId_QueryDsl(Long suggestId, Long memberId);

    public Page<SuggestLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long id);

}
