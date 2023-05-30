package com.app.projectjar.service.mypage;

import com.app.projectjar.domain.calendar.CalendarDTO;
import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.file.challenge.ChallengeFile;
import com.app.projectjar.entity.file.diary.DiaryFile;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface MyPageService {

    public void registerDiary(DiaryDTO diaryDTO,Long memberId);

    // 현재 시퀀스 가져오기
    public Diary getCurrentSequence();

    List<CalendarDTO> getCalendarDTO(Long memberId);

    public DiaryDTO getDiary(Long diaryId);

    public void modifyDiary(DiaryDTO diaryDTO);

    public void deleteDiary(Long diaryId);

    public Page<PersonalChallengeDTO> getChallengeList(String challengeStatus, Long memberId, Pageable pageable);

    public Page<GroupChallengeDTO> getGroupChallengeList(String challengeStatus, Long memberId, Pageable pageable);

    public MemberDTO getMemberDTO(Long memberId);

    public void modifyMember(MemberDTO memberDTO);

    public void withDrawMember(Long memberId);



    default DiaryDTO toDiaryDTO(Diary diary) {
        return DiaryDTO.builder()
                .memberDTO(toMemberDTO(diary.getMember()))
                .diaryStatus(diary.getDiaryStatus())
                .boardTitle(diary.getBoardTitle())
                .boardContent(diary.getBoardContent())
                .fileDTOS(fileToDTO(diary.getDiaryFiles()))
                .id(diary.getId())
                .start(diary.getCreatedDate())
                .end(diary.getCreatedDate())
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
                .createdDate(member.getCreatedDate())
                .badgeType(member.getBadgeType())
                .fileDTO(toFileDTO(member.getMemberFile()))
                .build();
    }


    default FileDTO toFileDTO(MemberFile memberFile) {
        if(memberFile == null){
            return null;
        }else {
            return FileDTO.builder()
                    .fileType(memberFile.getFileType())
                    .fileOriginalName(memberFile.getFileOriginalName())
                    .filePath(memberFile.getFilePath())
                    .fileUuid(memberFile.getFileUuid())
                    .build();
        }
    }

    default MemberFile toMemberFileEntity(FileDTO fileDTO) {
        return MemberFile.builder()
                .fileUuid(fileDTO.getFileUuid())
                .fileType(fileDTO.getFileType())
                .member(fileDTO.getMember())
                .filePath(fileDTO.getFilePath())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .build();
    }

    default List<FileDTO> fileToDTO(List<DiaryFile> diaryFiles) {
        List<FileDTO> diaryFileDTOList = new ArrayList<>();
        diaryFiles.forEach(
                diaryFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(diaryFile.getId())
                            .fileOriginalName(diaryFile.getFileOriginalName())
                            .fileUuid(diaryFile.getFileUuid())
                            .filePath(diaryFile.getFilePath())
                            .fileType(diaryFile.getFileType())
                            .build();
                    diaryFileDTOList.add(fileDTO);
                }
        );
        return diaryFileDTOList;
    }

    default Diary toDiaryEntity(DiaryDTO diaryDTO){
        return Diary.builder()
                .boardContent(diaryDTO.getBoardContent())
                .boardTitle(diaryDTO.getBoardTitle())
                .diaryStatus(diaryDTO.getDiaryStatus())
                .member(toMemberEntity(diaryDTO.getMemberDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .build();
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

    default PersonalChallengeDTO toPersonalChallengeDTO(ChallengeAttend challengeAttend) {
        return PersonalChallengeDTO.builder()
                .id(challengeAttend.getPersonalChallenge().getId())
                .challengeStatus(challengeAttend.getPersonalChallenge().getChallengeStatus())
                .challengeDTO(toChallengeDTO(challengeAttend.getPersonalChallenge().getChallenge()))
                .replyCount(challengeAttend.getPersonalChallenge().getChallengeReplyCount())
                .attendCount(challengeAttend.getPersonalChallenge().getChallengeAttendCount())
                .challengeAttendStatus(challengeAttend.getChallengeAttendStatus())
                .build();
    }

    default ChallengeDTO toChallengeDTO(Challenge challenge) {
        return ChallengeDTO.builder()
                .boardContent(challenge.getBoardContent())
                .boardTitle(challenge.getBoardTitle())
                .fileDTOS(toFileDTOS(challenge.getChallengeFiles()))
                .createdDate(challenge.getCreatedDate())
                .id(challenge.getId())
                .build();
    }

    default List<FileDTO> toFileDTOS(List<ChallengeFile> challengeFileList) {
        List<FileDTO> fileDTOS = new ArrayList<>();

        challengeFileList.forEach(
                challengeFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(challengeFile.getId())
                            .fileOriginalName(challengeFile.getFileOriginalName())
                            .fileUuid(challengeFile.getFileUuid())
                            .filePath(challengeFile.getFilePath())
                            .fileType(challengeFile.getFileType())
                            .build();
                    fileDTOS.add(fileDTO);
                }
        );
        return fileDTOS;
    }

    default List<FileDTO> toGroupChallengeFileDTOS(List<GroupChallengeFile> groupChallengeFiles) {
        List<FileDTO> fileDTOS = new ArrayList<>();

        groupChallengeFiles.forEach(
                groupChallengeFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(groupChallengeFile.getId())
                            .fileOriginalName(groupChallengeFile.getFileOriginalName())
                            .fileUuid(groupChallengeFile.getFileUuid())
                            .filePath(groupChallengeFile.getFilePath())
                            .fileType(groupChallengeFile.getFileType())
                            .build();
                    fileDTOS.add(fileDTO);
                }
        );
        return fileDTOS;
    }


    default GroupChallengeDTO toGroupChallengeDTO(GroupChallengeAttend groupChallengeAttend) {
        return GroupChallengeDTO.builder()
                .attendCount(groupChallengeAttend.getGroupChallenge().getGroupChallengeAttendCount())
                .replyCount(groupChallengeAttend.getGroupChallenge().getGroupChallengeReplyCount())
                .fileDTOS(toGroupChallengeFileDTOS(groupChallengeAttend.getGroupChallenge().getGroupChallengeFiles()))
                .boardContent(groupChallengeAttend.getGroupChallenge().getBoardContent())
                .boardTitle(groupChallengeAttend.getGroupChallenge().getBoardTitle())
                .groupChallengeAttendStatus(groupChallengeAttend.getGroupChallengeAttendStatus())
                .groupChallengeStatus(groupChallengeAttend.getGroupChallenge().getGroupChallengeStatus())
                .build();
    }
}
