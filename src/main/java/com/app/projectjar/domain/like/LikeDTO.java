package com.app.projectjar.domain.like;

import com.app.projectjar.domain.file.FileDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class LikeDTO {
    private Long likeId;
    private Long memberId;
    private Long boardId;

    @Builder
    public LikeDTO(Long likeId, Long memberId, Long boardId) {
        this.likeId = likeId;
        this.memberId = memberId;
        this.boardId = boardId;
    }
}
