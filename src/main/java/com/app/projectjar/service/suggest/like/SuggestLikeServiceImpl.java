package com.app.projectjar.service.suggest.like;

import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.suggest.SuggestLikeDTO;
import com.app.projectjar.entity.suggest.SuggestLike;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestLikeRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Qualifier("suggestLike") @Primary
@Slf4j
public class SuggestLikeServiceImpl implements SuggestLikeService {

    private final SuggestRepository suggestRepository;

    private final MemberRepository memberRepository;

    private final SuggestLikeRepository suggestLikeRepository;

    //하트 ++
    @Override
    public void heartUp(LikeDTO likeDTO) {
        memberRepository.findById(likeDTO.getMemberId()).ifPresent(
                member -> suggestRepository.findById(likeDTO.getBoardId()).ifPresent(
                        suggest -> {
                            SuggestLike suggestLike = SuggestLike.builder()
                                    .member(member)
                                    .suggest(suggest)
                                    .build();
                            suggestLikeRepository.save(suggestLike);
                            suggest.setSuggestLikeCount(getHeartCount(likeDTO.getBoardId()));
                            suggestRepository.save(suggest);
                        }
                )
        );
    }

    //    하트 --
    @Override
    public void heartDown(LikeDTO likeDTO) {
        suggestLikeRepository.deleteByMemberIdAndSuggestId_QueryDsl(likeDTO.getBoardId(),likeDTO.getMemberId());
        suggestRepository.findById(likeDTO.getBoardId()).ifPresent(
                suggest -> suggest.setSuggestLikeCount(getHeartCount(likeDTO.getBoardId()))
        );
    }

    //    하트 체크
    @Override
    public Boolean heartCheck(LikeDTO likeDTO) {
        Long member = suggestLikeRepository.findMemberBySuggestLike_QueryDsl(likeDTO.getBoardId(),likeDTO.getMemberId());
        return member == 0;
    }

    // 좋아요 갯수
    @Override
    public Integer getHeartCount(Long suggestId) {
        return suggestLikeRepository.getSuggestLikeCount_QueryDsl(suggestId).intValue();
    }

//      마이 페이지
    @Override
    public Page<SuggestLikeDTO> getLikeSuggestForMemberIdList(Pageable pageable, Long id) {
        Page<SuggestLike> suggestLikes = suggestLikeRepository.findByLikeMemberIdWithPaging_QueryDsl(pageable, id);
        List<SuggestLikeDTO> suggestLikeDTOS = suggestLikes.stream().map(this::toSuggestLikeDTO).collect(Collectors.toList());
        return new PageImpl<>(suggestLikeDTOS, suggestLikes.getPageable(), suggestLikes.getTotalElements());

    }

    }

