package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.entity.inquire.Answer;

public interface AnswerService {

    // 상세 보기
    public AnswerDTO getAnswer(Long answerId);

    // 작성하기
    public void register(Long inquireId, AnswerDTO answerDTO);

    default AnswerDTO toAnswerDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .answerContent(answer.getAnswerContent())
                .registerDate(answer.getCreatedDate())
                .build();
    }

    default Answer toAnswerEntity(AnswerDTO answerDTO){
        return Answer.builder()
                .answerContent(answerDTO.getAnswerContent())
                .inquire(answerDTO.getInquire())
                .build();
    }

}
