package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Inquire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InquireQueryDsl {
//    전체조회
    public Page<Inquire> findAllWithPaging_QueryDSL(Pageable pageable);

    /* 마이 페이지  */
//   내가 작성한 문의 게시글 전체 조회
    public Page<Inquire> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id);

    public void deleteByInquireId(Long inquireId);

}
