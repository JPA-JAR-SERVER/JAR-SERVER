package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.inquire.AnswerRepository;
import com.app.projectjar.repository.inquire.InquireRepository;
import com.app.projectjar.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@Service
@Qualifier("inquire") @Primary
@Slf4j
@RequiredArgsConstructor
public class InquireServiceImpl implements InquireService {
    private final InquireRepository inquireRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;

    @Override
    public Page<InquireDTO> getAllInquiresWithPaging(int page) {
        Page<Inquire> inquires = inquireRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<InquireDTO> inquireDTOS = inquires.getContent().stream()
                .map(this::toInquireDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(inquireDTOS, inquires.getPageable(), inquires.getTotalElements());
    }

    /*여기 여기 여기*/
    @Override
    public void register(InquireDTO inquireDTO, Long id) {
        inquireDTO.setMemberDTO(toMemberDTO(memberRepository.findById(id).get()));
        inquireRepository.save(toInquireEntity(inquireDTO));
    }

    @Override
    public InquireDTO getInquire(Long inquireId) {
        Optional<Inquire> inquire = inquireRepository.findById(inquireId);
        return toInquireDTO(inquire.get());
    }

    @Override
    public void deleteInquire(Long inquireId) { inquireRepository.deleteById(inquireId); }

    @Override
    public void updateInquire(Long inquireId, InquireDTO inquireDTO) {
        Optional<Inquire> optionalInquire = inquireRepository.findById(inquireId);
        if (optionalInquire.isPresent()) {
            Inquire existingInquire = optionalInquire.get();
            existingInquire.setInquireTitle(inquireDTO.getInquireTitle());
            existingInquire.setInquireContent(inquireDTO.getInquireContent());
            inquireRepository.save(existingInquire);
        } else {
        }
    }

    @Override
    public Page<InquireDTO> getInquireForMemberIdList(Pageable pageable, Long id) {
        Page<Inquire> inquires = inquireRepository.findAllByMemberIdWithPaging_QueryDsl(pageable, id);
        List<InquireDTO> inquireDTOS = inquires.stream().map(this::toInquireDTO).collect(Collectors.toList());
        return new PageImpl<>(inquireDTOS, inquires.getPageable(), inquires.getTotalElements());
    }

    @Override
    public void deleteInquires(List<Long> inquireIds) {
        for (Long inquireId : inquireIds) {
            answerRepository.deleteByAnswerId(inquireId);
            inquireRepository.deleteByInquireId(inquireId);
        }
    }


}
