package com.app.projectjar.search.board;

import lombok.Builder;
import lombok.Data;

@Data
public class GroupChallengeSearch {
    private String boardTitle;
    private Integer attendCount;
    private Integer replyCount;

    @Builder
    public GroupChallengeSearch(String boardTitle, Integer attendCount, Integer replyCount) {
        this.boardTitle = boardTitle;
        this.attendCount = attendCount;
        this.replyCount = replyCount;
    }
}
