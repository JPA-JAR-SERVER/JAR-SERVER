package com.app.projectjar.service.diary.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.entity.diary.DiaryReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.diary.DiaryReplyRepository;
import com.app.projectjar.repository.diary.DiaryRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("diaryReply") @Primary
public class DiaryReplyServiceImpl implements DiaryReplyService {
    private final DiaryReplyRepository diaryReplyRepository;

    private final MemberRepository memberRepository;

    private final DiaryRepository diaryRepository;

    @Override @Transactional
    public void insertReply(ReplyRequestDTO replyRequestDTO) {
        memberRepository.findById(replyRequestDTO.getMemberId()).ifPresent(
                member ->
                        diaryRepository.findById(replyRequestDTO.getBoardId()).ifPresent(
                                diary -> {
                                    DiaryReply diaryReply = DiaryReply.builder()
                                            .diary(diary)
                                            .member(member)
                                            .replyContent(replyRequestDTO.getReplyContent())
                                            .build();
                                    diaryReplyRepository.save(diaryReply);
                                    diary.setDiaryReplyCount(getReplyCount(replyRequestDTO.getBoardId()));
                                    diaryRepository.save(diary);
                                }
                        )
        );
    }

    @Override @Transactional
    public void modifyReply(Long replyId, String replyContent) {
        diaryReplyRepository.findById(replyId).ifPresent(
                diaryReply -> {
                    diaryReply.setDiaryReplyContent(replyContent);
                    diaryReplyRepository.save(diaryReply);
                }
        );
    }

    @Override
    public void deleteReply(Long replyId) {
        diaryReplyRepository.findById(replyId).ifPresent(
                diaryReply -> {
                    diaryReplyRepository.delete(diaryReply);
                    diaryRepository.findById(diaryReply.getDiary().getId()).ifPresent(
                            diary -> {
                                diary.setDiaryReplyCount(getReplyCount(replyId));
                                diaryRepository.save(diary);
                            }
                    );
                }
        );
    }

    @Override
    public Slice<ReplyDTO> getReplyList(Long diaryId, Pageable pageable) {
        Slice<DiaryReply> diaryReplyList = diaryReplyRepository.findAllByDiaryWithPaging_QueryDsl(diaryId, pageable);

        List<ReplyDTO> replyDTOS = diaryReplyList.getContent().stream().map(this::toReplyDTO).collect(Collectors.toList());
        return new SliceImpl<>(replyDTOS, pageable, diaryReplyList.hasNext());
    }

    @Override
    public Integer getReplyCount(Long diaryId) {
        return diaryReplyRepository.getReplyCount_QueryDsl(diaryId).intValue();
    }
}
