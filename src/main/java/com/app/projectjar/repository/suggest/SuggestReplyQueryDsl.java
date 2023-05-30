package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SuggestReplyQueryDsl {

    // 댓글 저장 save
    // 댓글 수정 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 댓글 삭제 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 전체 조회 ( 페이징 )
    public Slice<SuggestReply> findAllBySuggestWithPaging_QueryDsl(Long suggestId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long suggestId);
    // 삭제
    public void deleteBySuggestId(Long suggestId);
}
