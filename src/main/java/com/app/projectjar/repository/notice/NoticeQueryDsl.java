package com.app.projectjar.repository.notice;

import com.app.projectjar.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryDsl {
    // 전체 조회
    public Page<Notice> findAllWithPaging_QueryDSL(Pageable pageable);
}
