package com.app.projectjar.service.suggest.like;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.domain.suggest.SuggestLikeDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface SuggestLikeService {

    // 좋아요 갯수
    public Integer getHeartCount(Long suggestId);
    // 좋아요 ++
    public void heartUp(LikeDTO likeDTO);
    // 좋아요 --
    public void heartDown(LikeDTO likeDTO);
    // 좋아요 한 게시물인지 검사
    public Boolean heartCheck(LikeDTO likeDTO);


//    마이 페이지
    public Page<SuggestLikeDTO> getLikeSuggestForMemberIdList(Pageable pageable, Long id);

    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .userType(member.getUserType())
                .memberType(member.getMemberType())
                .build();
    }

    default SuggestDTO toSuggestDTO(Suggest suggest) {
        return SuggestDTO.builder()
                .id(suggest.getId())
                .boardTitle(suggest.getBoardTitle())
                .boardContent(suggest.getBoardContent())
                .boardType(suggest.getBoardType())
                .likeCount(suggest.getSuggestLikeCount())
                .replyCount(suggest.getSuggestReplyCount())
                .fileDTOS(FileToDTO(suggest.getSuggestFiles()))
                .createdDate(suggest.getCreatedDate())
                .updatedDate(suggest.getUpdatedDate())
                .memberDTO(toMemberDTO(suggest.getMember()))
                .build();
    }

    default List<FileDTO> FileToDTO(List<SuggestFile> suggestFiles) {
        List<FileDTO> suggestFileList = new ArrayList<>();
        suggestFiles.forEach(
                suggestFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(suggestFile.getId())
                            .fileOriginalName(suggestFile.getFileOriginalName())
                            .fileUuid(suggestFile.getFileUuid())
                            .filePath(suggestFile.getFilePath())
                            .fileType(suggestFile.getFileType())
                            .build();
                    suggestFileList.add(fileDTO);
                }
        );
        return suggestFileList;
    }

    default LikeDTO toLikeDTO(SuggestLike suggestLike){
        return LikeDTO.builder()
                .boardId(suggestLike.getSuggest().getId())
                .memberId(suggestLike.getMember().getId())
                .likeId(suggestLike.getId())
                .build();
    }

    default SuggestLikeDTO toSuggestLikeDTO(SuggestLike suggestLike){
        return SuggestLikeDTO.builder()
                .suggestLikeId(suggestLike.getId())
                .memberId(suggestLike.getMember().getId())
                .boardId(suggestLike.getSuggest().getId())
                .suggestDTO(toSuggestDTO(suggestLike.getSuggest()))
                .memberDTO(toMemberDTO(suggestLike.getMember()))
                .build();
    }

}
