package com.app.projectjar.service.diary.like;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.diary.DiaryLikeDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.diary.DiaryLike;
import com.app.projectjar.entity.file.diary.DiaryFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.SuggestLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface DiaryLikeService {

    // 좋아요 갯수
    public Integer getHeartCount(Long diaryId);
    // 좋아요 ++
    public void heartUp(LikeDTO likeDTO);
    // 좋아요 --
    public void heartDown(LikeDTO likeDTO);
    // 좋아요 한 게시물인지 검사
    public Boolean heartCheck(LikeDTO likeDTO);

//    내가 좋요한 다이어리 게시물
    public Page<DiaryLikeDTO> getLikeDiaryForMemberIdList(Pageable pageable, Long id);

    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .memberType(member.getMemberType())
                .userType(member.getUserType())
                .build();
    }

    default DiaryDTO toDiaryDTO(Diary diary) {
        return DiaryDTO.builder()
                .boardContent(diary.getBoardContent())
                .boardTitle(diary.getBoardTitle())
                .diaryStatus(diary.getDiaryStatus())
                .fileDTOS(FileToDTO(diary.getDiaryFiles()))
                .id(diary.getId())
                .likeCount(diary.getDiaryLikeCount())
                .memberDTO(toMemberDTO(diary.getMember()))
                .replyCount(diary.getDiaryReplyCount())
                .createDate(diary.getCreatedDate())
                .build();
    }

    default List<FileDTO> FileToDTO(List<DiaryFile> diaryFiles) {
        List<FileDTO> diaryFileList = new ArrayList<>();
        diaryFiles.forEach(
                diaryFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(diaryFile.getId())
                            .fileOriginalName(diaryFile.getFileOriginalName())
                            .fileUuid(diaryFile.getFileUuid())
                            .filePath(diaryFile.getFilePath())
                            .fileType(diaryFile.getFileType())
                            .build();
                    diaryFileList.add(fileDTO);
                }
        );
        return diaryFileList;
    }

    default DiaryFile toDiaryFileEntity(FileDTO fileDTO){
        return DiaryFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .diary(fileDTO.getDiary())
                .fileType(fileDTO.getFileType())
                .build();
    }

    default LikeDTO toLikeDTO(SuggestLike suggestLike){
        return LikeDTO.builder()
                .boardId(suggestLike.getSuggest().getId())
                .memberId(suggestLike.getMember().getId())
                .likeId(suggestLike.getId())
                .build();
    }

    default DiaryLikeDTO toDiaryLikeDTO(DiaryLike diaryLike){
        return DiaryLikeDTO.builder()
                .diaryLikeId(diaryLike.getId())
                .memberId(diaryLike.getId())
                .boardId(diaryLike.getId())
                .diaryDTO(toDiaryDTO(diaryLike.getDiary()))
                .memberDTO(toMemberDTO(diaryLike.getMember()))
                .build();
    }
}
