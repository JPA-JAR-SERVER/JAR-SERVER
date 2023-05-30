package com.app.projectjar.service.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.file.diary.DiaryFile;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public interface DiaryService {
    //    전체 목록 페이징
    public Page<DiaryDTO> getAllDiarysWithPaging(int page);
//    목록
    public Slice<DiaryDTO> getOpenDiaryList(String sort, Pageable pageable);
//    상세보기
    public DiaryDTO getDiary(Long diaryId);

//  마이 페이지 공유 일기 목록 조회
    public Page<DiaryDTO> getDiaryForMemberIdList(Pageable pageable, Long id);

//    수정
    public void modifyDiary(DiaryDTO diaryDTO);

//    삭제
    public void delete(Long diaryId);

    // 삭제
    public void deleteDiaries(List<Long> diaryIds);

    // 현재 시퀀스 가져오기
    public Diary getCurrentSequence();

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

    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
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

}
