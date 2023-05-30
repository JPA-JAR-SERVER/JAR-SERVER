package com.app.projectjar.service.groupChallenge.reply;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.groupChallenge.GroupChallengeReply;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GroupChallengeReplyService {

    // 댓글 저장
    public void insertReply(ReplyRequestDTO replyRequestDTO);
    // 댓글 수정
    public void modifyReply(Long replyId, String replyContent);
    // 댓글 삭제
    public void deleteReply(Long replyId);
    // 댓글 목록
    public Slice<ReplyDTO> getReplyList(Long groupChallengeId, Pageable pageable);
    // 댓글 갯수
    public Integer getReplyCount(Long groupChallengeId);

    default ReplyDTO toReplyDTO(GroupChallengeReply groupChallengeReply){
            return ReplyDTO.builder()
                    .id(groupChallengeReply.getId())
                    .registerDate(groupChallengeReply.getUpdatedDate())
                    .memberDTO(toMemberDTO(groupChallengeReply.getMember()))
                    .replyContent(groupChallengeReply.getReplyContent())
                    .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .badgeType(member.getBadgeType())
                .fileDTO(toFileDTO(member.getMemberFile()))
                .memberEmail(member.getMemberEmail())
                .id(member.getId())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    default FileDTO toFileDTO(MemberFile memberFile) {
        if(memberFile == null){
            return null;
        }

        return FileDTO.builder()
                .fileOriginalName(memberFile.getFileOriginalName())
                .filePath(memberFile.getFilePath())
                .fileUuid(memberFile.getFileUuid())
                .id(memberFile.getId())
                .build();
    }

}
