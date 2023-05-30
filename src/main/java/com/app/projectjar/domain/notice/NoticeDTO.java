package com.app.projectjar.domain.notice;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class NoticeDTO {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public NoticeDTO(Long id, String noticeTitle, String noticeContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
