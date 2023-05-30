package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestReplyRepository extends JpaRepository<SuggestReply, Long>, SuggestReplyQueryDsl {
    // 댓글 개수
    // 댓글 작성자 정보 및 조회
    // 댓글 목록(페이징)
    
}
