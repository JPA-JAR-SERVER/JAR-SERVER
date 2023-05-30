package com.app.projectjar.domain.reply;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.type.BadgeType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {

    private Long id;
    private String replyContent;
    private LocalDateTime registerDate;

    private MemberDTO memberDTO;
    private SuggestDTO suggestDTO;

    @Builder
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, MemberDTO memberDTO,SuggestDTO suggestDTO) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
        this.suggestDTO = suggestDTO;
    }

    @Builder
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, MemberDTO memberDTO) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
    }
}
