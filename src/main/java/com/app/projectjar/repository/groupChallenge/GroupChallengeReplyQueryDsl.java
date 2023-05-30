package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallengeReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GroupChallengeReplyQueryDsl {

    // 댓글 저장 save
    // 댓글 수정 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 댓글 삭제 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 전체 조회 ( 페이징 )
    public Slice<GroupChallengeReply> findAllBySuggestWithPaging_QueryDsl(Long groupChallengeId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long groupChallengeId);
    // 삭제
    public void deleteBySuggestId(Long groupChallengeId);

    public void deleteByGroupChallengeId(Long groupChallengeId);
}
