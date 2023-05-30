package com.app.projectjar.service.suggest.reply;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SuggestReplyService {

    // 댓글 저장
    public void insertReply(ReplyRequestDTO replyRequestDTO);
    // 댓글 수정
    public void modifyReply(Long replyId, String replyContent);
    // 댓글 삭제
    public void deleteReply(Long replyId);
    // 댓글 목록
    public Slice<ReplyDTO> getReplyList(Long suggestId, Pageable pageable);
    // 댓글 갯수
    public Integer getReplyCount(Long suggestId);

    default SuggestReply toSuggestReplyEntity(ReplyDTO replyDTO){
        return SuggestReply.builder()
                .id(replyDTO.getId())
                .member(toMemberEntity(replyDTO.getMemberDTO()))
                .replyContent(replyDTO.getReplyContent())
                .suggest(toSuggestEntity(replyDTO.getSuggestDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .badgeType(memberDTO.getBadgeType())
                .memberStatus(memberDTO.getMemberStatus())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberPassword(memberDTO.getMemberPassword())
                .memberNickname(memberDTO.getMemberNickname())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberFile(toMemberFileEntity(memberDTO.getFileDTO()))
                .build();
    }

    default MemberFile toMemberFileEntity(FileDTO fileDTO){
        return MemberFile.builder()
                .fileOriginalName(fileDTO.getFileOriginalName())
                .filePath(fileDTO.getFilePath())
                .fileType(fileDTO.getFileType())
                .fileUuid(fileDTO.getFileUuid())
                .id(fileDTO.getId())
                .build();
    }

    default Suggest toSuggestEntity(SuggestDTO suggestDTO){
        return Suggest.builder()
                .id(suggestDTO.getId())
                .boardContent(suggestDTO.getBoardContent())
                .boardTitle(suggestDTO.getBoardTitle())
                .boardType(suggestDTO.getBoardType())
                .build();
    }

    default ReplyDTO toReplyDTO(SuggestReply suggestReply) {
        return ReplyDTO.builder()
                .id(suggestReply.getId())
                .memberDTO(toMemberDTO(suggestReply.getMember()))
                .registerDate(suggestReply.getUpdatedDate())
                .replyContent(suggestReply.getReplyContent())
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
