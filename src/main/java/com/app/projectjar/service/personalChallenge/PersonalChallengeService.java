package com.app.projectjar.service.personalChallenge;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.file.challenge.ChallengeFile;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface PersonalChallengeService {

    // 목록 가져오기
    public Page<PersonalChallengeDTO> getListByChallengeStatus(String challengeStatus, Pageable pageable);

    // 어제 insert된 목록
    public List<PersonalChallenge> getListToYesterday(LocalDate yesterday);

    //    전체 목록 페이징
    public Page<ChallengeDTO> getAllChallengesWithPaging(int page);

    // 삭제
    public void deleteChallenges(List<Long> challengeIds);

    // 상세보기
    public ChallengeDTO getChallengePersonal(Long challengeId);

    // 수정
    public void update(ChallengeDTO challengeDTO);

    // 삭제
    public void delete(Long challengeId);

    // 현재 시퀀스 가져오기
    public Challenge getCurrentSequence();

    // challengeStatus private로 수정
    public void updateChallengeStatus(List<PersonalChallenge> challengeList);

    // 상세보기
    public PersonalChallengeDTO getPersonalChallenge(Long personalChallengeId);

    // 등록된 챌린지 목록 전체 조회
    public List<Challenge> getChallengeList();

    // 챌린지 등록
    public void insertChallenge(Challenge challenge);

    // 저장
    public void register(ChallengeDTO challengeDTO);

    // 상세보기
    public ChallengeDTO getChallenge(Long challengeId);


    default PersonalChallengeDTO toPersonalChallengeDTO(PersonalChallenge personalChallenge) {
        return PersonalChallengeDTO.builder()
                .attendCount(personalChallenge.getChallengeAttendCount())
                .replyCount(personalChallenge.getChallengeReplyCount())
                .challengeDTO(toChallengeDTO(personalChallenge.getChallenge()))
                .challengeStatus(personalChallenge.getChallengeStatus())
                .id(personalChallenge.getId())
                .startDate(personalChallenge.getCreatedDate())
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

    default Challenge toChallengeEntity(ChallengeDTO challengeDTO){
        return Challenge.builder()
                .id(challengeDTO.getId())
                .boardTitle(challengeDTO.getBoardTitle())
                .boardContent(challengeDTO.getBoardContent())
                .build();
    }

    default ChallengeFile toChallengeFileEntity(FileDTO fileDTO){
        return ChallengeFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .challenge(fileDTO.getChallenge())
                .fileType(fileDTO.getFileType())
                .build();
    }
}
