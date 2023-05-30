package com.app.projectjar.service.personalChallenge.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.personalChallenge.ChallengeReply;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.challenge.ChallengeRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.personalChallenge.ChallengeReplyRepository;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
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
@Qualifier("challengeReply") @Primary
public class ChallengeReplyServiceImpl implements ChallengeReplyService {

    private final ChallengeReplyRepository challengeReplyRepository;

    private final MemberRepository memberRepository;

    private final PersonalChallengeRepository personalChallengeRepository;

    @Override @Transactional
    public void insertReply(ReplyRequestDTO replyRequestDTO) {
        memberRepository.findById(replyRequestDTO.getMemberId()).ifPresent(
                member ->
                        personalChallengeRepository.findById(replyRequestDTO.getBoardId()).ifPresent(
                                personalChallenge -> {
                                    ChallengeReply challengeReply = ChallengeReply.builder()
                                            .personalChallenge(personalChallenge)
                                            .member(member)
                                            .replyContent(replyRequestDTO.getReplyContent())
                                            .build();
                                    challengeReplyRepository.save(challengeReply);
                                    personalChallenge.setChallengeReplyCount(getReplyCount(replyRequestDTO.getBoardId()));
                                    personalChallengeRepository.save(personalChallenge);
                                }
                        )
        );
    }

    @Override
    public void modifyReply(Long replyId, String replyContent) {
        challengeReplyRepository.findById(replyId).ifPresent(
                challengeReply -> {
                    challengeReply.setChallengeReplyContent(replyContent);
                    challengeReplyRepository.save(challengeReply);
                }
        );
    }

    @Override
    public void deleteReply(Long replyId) {
        challengeReplyRepository.findById(replyId).ifPresent(
                challengeReply -> {
                    challengeReplyRepository.delete(challengeReply);
                    personalChallengeRepository.findById(challengeReply.getPersonalChallenge().getId()).ifPresent(
                            personalChallenge -> {
                                personalChallenge.setChallengeReplyCount(getReplyCount(replyId));
                                personalChallengeRepository.save(personalChallenge);
                            }
                    );
                }
        );
    }

    @Override
    public Slice<ReplyDTO> getReplyList(Long personalChallengeId, Pageable pageable) {
        Slice<ChallengeReply> challengeReplyList = challengeReplyRepository.findAllByChallengeWithPaging(personalChallengeId, pageable);
        List<ReplyDTO> replyDTOS = challengeReplyList.getContent().stream().map(this::toReplyDTO).collect(Collectors.toList());
        return new SliceImpl<>(replyDTOS, pageable, challengeReplyList.hasNext());
    }

    @Override
    public Integer getReplyCount(Long personalChallengeId) {
        return challengeReplyRepository.getReplyCount(personalChallengeId).intValue();
    }
}
