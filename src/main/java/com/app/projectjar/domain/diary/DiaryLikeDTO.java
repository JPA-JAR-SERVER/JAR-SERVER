package com.app.projectjar.domain.diary;


import com.app.projectjar.domain.member.MemberDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DiaryLikeDTO {

    private Long diaryLikeId;
    private Long memberId;
    private Long boardId;
    private DiaryDTO diaryDTO;
    private MemberDTO memberDTO;

    @Builder
    public DiaryLikeDTO(Long diaryLikeId, Long memberId, Long boardId, DiaryDTO diaryDTO, MemberDTO memberDTO) {
        this.diaryLikeId = diaryLikeId;
        this.memberId = memberId;
        this.boardId = boardId;
        this.diaryDTO = diaryDTO;
        this.memberDTO = memberDTO;
    }
}
