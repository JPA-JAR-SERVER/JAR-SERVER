package com.app.projectjar.domain.challenge;

import com.app.projectjar.domain.file.FileDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
@NoArgsConstructor
public class ChallengeDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createdDate;

    private List<FileDTO> fileDTOS;

    @Builder
    public ChallengeDTO(Long id, String boardTitle, String boardContent, List<FileDTO> fileDTOS, LocalDateTime createdDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.fileDTOS = fileDTOS;
        this.createdDate = createdDate;
    }

}
