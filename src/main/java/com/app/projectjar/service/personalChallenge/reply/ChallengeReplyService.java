package com.app.projectjar.service.personalChallenge.reply;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.personalChallenge.ChallengeReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ChallengeReplyService {

    // 댓글 저장
    public void insertReply(ReplyRequestDTO replyRequestDTO);
    // 댓글 수정
    public void modifyReply(Long replyId, String replyContent);
    // 댓글 삭제
    public void deleteReply(Long replyId);
    // 댓글 목록
    public Slice<ReplyDTO> getReplyList(Long personalChallengeId, Pageable pageable);
    // 댓글 갯수
    public Integer getReplyCount(Long personalChallengeId);

    default ReplyDTO toReplyDTO(ChallengeReply challengeReply) {
        return ReplyDTO.builder()
                .id(challengeReply.getId())
                .memberDTO(toMemberDTO(challengeReply.getMember()))
                .registerDate(challengeReply.getUpdatedDate())
                .replyContent(challengeReply.getReplyContent())
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
