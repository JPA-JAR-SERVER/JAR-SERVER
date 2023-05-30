package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.repository.inquire.AnswerRepository;
import com.app.projectjar.repository.inquire.InquireRepository;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.type.AnswerType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Qualifier("answer") @Primary
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final InquireRepository inquireRepository;

    //    상세보기
    @Override
    public AnswerDTO getAnswer(Long answerId) {
        Optional<Answer> answer = answerRepository.findByIdAnswer_QueryDsl(answerId);
        return toAnswerDTO(answer.get());
    }

    @Override @Transactional
    public void register(Long inquireId, AnswerDTO answerDTO) {

        inquireRepository.findById(inquireId).ifPresent(
                inquire -> {
                    inquire.setAnswerType(AnswerType.ANSWER);
                    inquireRepository.save(inquire);
                    answerDTO.setInquire(inquire);
                    answerRepository.save(toAnswerEntity(answerDTO));
                }
        );
    }

}
