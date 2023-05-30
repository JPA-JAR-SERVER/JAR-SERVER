package com.app.projectjar.domain.diary;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.type.DiaryType;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter @ToString
@NoArgsConstructor
public class DiaryDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private DiaryType diaryStatus;
    private LocalDateTime start;
    private LocalDateTime end;
    private Integer likeCount;
    private Integer replyCount;
    private MemberDTO memberDTO;
    private List<FileDTO> fileDTOS;

    private LocalDateTime createDate;

    @Builder
    public DiaryDTO(Long id, String boardTitle, String boardContent, DiaryType diaryStatus, LocalDateTime start, LocalDateTime end, Integer likeCount, Integer replyCount, MemberDTO memberDTO, List<FileDTO> fileDTOS, LocalDateTime createDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.diaryStatus = diaryStatus;
        this.start = start;
        this.end = end;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
        this.fileDTOS = fileDTOS;
        this.createDate = createDate;
    }
}
