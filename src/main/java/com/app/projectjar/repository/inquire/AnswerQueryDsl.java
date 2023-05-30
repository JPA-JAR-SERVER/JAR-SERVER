package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface AnswerQueryDsl {
    // 댓글 저장 save
    // 댓글 수정 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 댓글 삭제 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 전체 조회 ( 페이징 )
//    public Slice<Answer> findAllByAnswerWithPaging_QueryDsl(Long id, Pageable pageable);
//    // 댓글 갯수
//    public Long getAnswerCount_QueryDsl(Long id);
//    // 삭제
//    public void deleteByAnswerId(Long id);

    // 상세보기
    public Optional<Answer> findByIdAnswer_QueryDsl(Long answerId);

    public void deleteByAnswerId(Long answerId);
    
}
