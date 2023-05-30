package com.app.projectjar.search.board;

import com.app.projectjar.domain.member.MemberDTO;
import lombok.Builder;
import lombok.Data;

import javax.management.Query;


@Data
public class SuggestSearch {
    private String boardTitle;
    private Integer likeCount;
    private Integer replyCount;
    private MemberDTO memberDTO;

    @Builder
    public SuggestSearch(String boardTitle, Integer likeCount, Integer replyCount, MemberDTO memberDTO) {
        this.boardTitle = boardTitle;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
    }
}
