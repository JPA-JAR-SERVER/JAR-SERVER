package com.app.projectjar.service.diary.like;

import com.app.projectjar.domain.diary.DiaryLikeDTO;
import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.entity.diary.DiaryLike;
import com.app.projectjar.repository.diary.DiaryLikeRepository;
import com.app.projectjar.repository.diary.DiaryRepository;
import com.app.projectjar.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("diaryLike") @Primary
@RequiredArgsConstructor
public class DiaryLikeServiceImpl implements DiaryLikeService {
    private final DiaryRepository diaryRepository;

    private final MemberRepository memberRepository;

    private final DiaryLikeRepository diaryLikeRepository;

    //하트 ++
    @Override
    public void heartUp(LikeDTO likeDTO) {
        memberRepository.findById(likeDTO.getMemberId()).ifPresent(
                member -> diaryRepository.findById(likeDTO.getBoardId()).ifPresent(
                        diary -> {
                            DiaryLike diaryLike = DiaryLike.builder()
                                    .member(member)
                                    .diary(diary)
                                    .build();
                            diaryLikeRepository.save(diaryLike);
                            diary.setDiaryLikeCount(getHeartCount(likeDTO.getBoardId()));
                            diaryRepository.save(diary);
                        }
                )
        );
    }

    //    하트 --
    @Override
    public void heartDown(LikeDTO likeDTO) {
        diaryLikeRepository.deleteByMemberIdAndDiaryId_QueryDsl(likeDTO.getBoardId(),likeDTO.getMemberId());
        diaryRepository.findById(likeDTO.getBoardId()).ifPresent(
                diary -> diary.setDiaryLikeCount(getHeartCount(likeDTO.getBoardId()))
        );
    }

    //    하트 체크
    @Override
    public Boolean heartCheck(LikeDTO likeDTO) {
        Long member = diaryLikeRepository.findMemberByDiaryLike_QueryDsl(likeDTO.getBoardId(),likeDTO.getMemberId());
        return member == 0;
    }

    @Override
    public Page<DiaryLikeDTO> getLikeDiaryForMemberIdList(Pageable pageable, Long id) {
        Page<DiaryLike> diaryLikes = diaryLikeRepository.findByLikeMemberIdWithPaging_QueryDsl(pageable, id);
        List<DiaryLikeDTO> diaryLikeDTOS = diaryLikes.stream().map(this::toDiaryLikeDTO).collect(Collectors.toList());
        return new PageImpl<>(diaryLikeDTOS, diaryLikes.getPageable(), diaryLikes.getTotalElements());
    }

    // 좋아요 갯수
    @Override
    public Integer getHeartCount(Long diaryId) {
        return diaryLikeRepository.getDiaryLikeCount_QueryDsl(diaryId).intValue();
    }
}
