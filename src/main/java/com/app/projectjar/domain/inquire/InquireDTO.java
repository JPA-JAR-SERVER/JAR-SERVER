package com.app.projectjar.domain.inquire;

import com.app.projectjar.audit.Period;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.AnswerType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Component
@NoArgsConstructor
public class InquireDTO {

    private AnswerDTO answerDTO;

    private Long id;
    private String inquireTitle;
    private String inquireContent;
    private AnswerType answerType;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private MemberDTO memberDTO;

    @Builder
    public InquireDTO(AnswerDTO answerDTO, Long id, String inquireTitle, String inquireContent, AnswerType answerType, LocalDateTime createdDate, LocalDateTime updatedDate, MemberDTO memberDTO) {
        this.answerDTO = answerDTO;
        this.id = id;
        this.inquireTitle = inquireTitle;
        this.inquireContent = inquireContent;
        this.answerType = answerType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.memberDTO = memberDTO;
    }
}
