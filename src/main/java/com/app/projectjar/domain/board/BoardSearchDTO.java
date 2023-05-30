package com.app.projectjar.domain.board;

import com.app.projectjar.search.board.GroupChallengeSearch;
import com.app.projectjar.search.board.SuggestSearch;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class BoardSearchDTO {
    private GroupChallengeSearch groupChallengeSearch;
    private SuggestSearch suggestSearch;

    @Builder
    public BoardSearchDTO(GroupChallengeSearch groupChallengeSearch, SuggestSearch suggestSearch) {
        this.groupChallengeSearch = groupChallengeSearch;
        this.suggestSearch = suggestSearch;
    }
}
