package com.app.projectjar.domain.suggest;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.type.BoardType;
import com.app.projectjar.domain.member.MemberDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class SuggestDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private BoardType boardType;

    private Integer likeCount;
    private Integer replyCount;

    private MemberDTO memberDTO;
    private List<FileDTO> fileDTOS;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LikeDTO likeDTO;

    public SuggestDTO() {
        this.fileDTOS = new ArrayList<>();
    }

    @Builder
    public SuggestDTO(Long id, String boardTitle, String boardContent, BoardType boardType, Integer likeCount, Integer replyCount, MemberDTO memberDTO, List<FileDTO> fileDTOS, LocalDateTime createdDate, LocalDateTime updatedDate, LikeDTO likeDTO) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
        this.fileDTOS = fileDTOS;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.likeDTO = likeDTO;
    }
}
