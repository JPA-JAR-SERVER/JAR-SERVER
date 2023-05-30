package com.app.projectjar.service.groupChallenge.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.entity.groupChallenge.GroupChallengeReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.groupChallenge.GroupChallengeReplyRepository;
import com.app.projectjar.repository.groupChallenge.GroupChallengeRepository;
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
@Qualifier("groupChallengeReply") @Primary
@RequiredArgsConstructor
public class GroupChallengeReplyServiceImpl implements GroupChallengeReplyService {

    private final GroupChallengeReplyRepository groupChallengeReplyRepository;

    private final MemberRepository memberRepository;

    private final GroupChallengeRepository groupChallengeRepository;

    @Override @Transactional
    public void insertReply(ReplyRequestDTO replyRequestDTO) {
        memberRepository.findById(replyRequestDTO.getMemberId()).ifPresent(
                member ->
                        groupChallengeRepository.findById(replyRequestDTO.getBoardId()).ifPresent(
                                groupChallenge -> {
                                    GroupChallengeReply groupChallengeReply = GroupChallengeReply.builder()
                                            .member(member)
                                            .groupChallenge(groupChallenge)
                                            .replyContent(replyRequestDTO.getReplyContent())
                                            .build();

                                    groupChallengeReplyRepository.save(groupChallengeReply);
                                    groupChallenge.setGroupChallengeReplyCount(getReplyCount(replyRequestDTO.getBoardId()));
                                    groupChallengeRepository.save(groupChallenge);
                                }
                        )
        );
    }

    @Override @Transactional
    public void modifyReply(Long replyId, String replyContent) {
        groupChallengeReplyRepository.findById(replyId).ifPresent(
                suggestReply -> {
                    suggestReply.setGroupChallengeReplyContent(replyContent);
                    groupChallengeReplyRepository.save(suggestReply);
                }
        );
    }

    @Override
    public void deleteReply(Long replyId) {
        groupChallengeReplyRepository.findById(replyId).ifPresent(
                groupChallengeReply -> {
                    groupChallengeReplyRepository.delete(groupChallengeReply);
                    groupChallengeRepository.findById(groupChallengeReply.getGroupChallenge().getId()).ifPresent(
                            groupChallenge -> {
                                groupChallenge.setGroupChallengeReplyCount(getReplyCount(replyId));
                                groupChallengeRepository.save(groupChallenge);
                            }
                    );
                }
        );
    }

    @Override
    public Slice<ReplyDTO> getReplyList(Long groupChallengeId, Pageable pageable) {
        Slice<GroupChallengeReply> groupChallengeReplies = groupChallengeReplyRepository.findAllBySuggestWithPaging_QueryDsl(groupChallengeId, pageable);

        List<ReplyDTO> replyDTOS = groupChallengeReplies.getContent().stream().map(this::toReplyDTO).collect(Collectors.toList());
        return new SliceImpl<>(replyDTOS, pageable, groupChallengeReplies.hasNext());
    }

    @Override
    public Integer getReplyCount(Long groupChallengeId) {
        return groupChallengeReplyRepository.getReplyCount_QueryDsl(groupChallengeId).intValue();
    }
}
