package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.SuggestLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestLikeRepository extends JpaRepository<SuggestLike, Long>, SuggestLikeQueryDsl {
    // 좋아요 ++ save
    // 좋아요 햇는지 검사
    // 좋아요 -- delete
    // 좋아요 갯수
}
