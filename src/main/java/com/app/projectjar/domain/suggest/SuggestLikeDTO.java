package com.app.projectjar.domain.suggest;

import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SuggestLikeDTO {

    private Long suggestLikeId;
    private Long memberId;
    private Long boardId;
    private SuggestDTO suggestDTO;
    private MemberDTO memberDTO;

    @Builder
    public SuggestLikeDTO(Long suggestLikeId, Long memberId, Long boardId, SuggestDTO suggestDTO, MemberDTO memberDTO) {
        this.suggestLikeId = suggestLikeId;
        this.memberId = memberId;
        this.boardId = boardId;
        this.suggestDTO = suggestDTO;
        this.memberDTO = memberDTO;
    }
}
