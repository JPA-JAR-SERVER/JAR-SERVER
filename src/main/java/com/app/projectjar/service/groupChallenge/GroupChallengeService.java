package com.app.projectjar.service.groupChallenge;

import com.app.projectjar.domain.board.BoardSearchDTO;
import com.app.projectjar.domain.calendar.CalendarDTO;
import com.app.projectjar.domain.calendar.GroupCalendarDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.search.board.GroupChallengeSearch;
import com.app.projectjar.search.board.SuggestSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface GroupChallengeService {

    // 목록
    public Page<GroupChallengeDTO> getGroupChallengeList(Pageable pageable);

    //    전체 목록 페이징
    public Page<GroupChallengeDTO> getAllGroupChallengesWithPaging(int page);

    // 캘린더
    public List<GroupCalendarDTO> findAllCalendar();

    // 종료된 목록
    public Page<GroupChallengeDTO> getEndGroupChallengeList(Pageable pageable);

    // 상세보기
    public GroupChallengeDTO getGroupChallenge(Long groupChallengeId);

    // 삭제
    public void deleteGroupChallenges(List<Long> groupChallengeIds);

    // 삭제
    public void delete(Long groupChallengeId);

    // 수정
    public void update(GroupChallengeDTO groupChallengeDTO);

    // 저장
    public void register(GroupChallengeDTO groupChallengeDTO);

    // 현재 시퀀스 가져오기
    public GroupChallenge getCurrentSequence();

    // 어제 날짜가 종료인 게시물 목록 가져오기
    public List<GroupChallenge> getListByEndDate(LocalDate endDate);

    // 오늘 날짜가 시작인 게시물 목록 가져오기
    public List<GroupChallenge> getListByStartDate(LocalDate startDate);

    // status OPEN으로 변경
    public void updateGroupChallengeTypeToOpen(List<GroupChallenge> groupChallengeList);

    // status PRIVATE로 변경
    public void updateGroupChallengeTypeToPrivate(List<GroupChallenge> groupChallengeList);

    // 검색
    public List<GroupChallengeDTO> findBoardSearch(String search);

    default BoardSearchDTO toBoardSearchDTO(GroupChallengeSearch groupChallengeSearch, SuggestSearch suggestSearch){
        return BoardSearchDTO.builder()
                .groupChallengeSearch(groupChallengeSearch)
                .suggestSearch(suggestSearch)
                .build();
    }

    default GroupCalendarDTO toCalendarDTO(GroupChallenge groupChallenge){
        return GroupCalendarDTO.builder()
                .id(groupChallenge.getId())
                .title(groupChallenge.getBoardTitle())
                .start(groupChallenge.getStartDate())
                .end(groupChallenge.getEndDate())
                .build();
    }

    default GroupChallengeDTO toGroupChallengeDTO(GroupChallenge groupChallenge) {
        return GroupChallengeDTO.builder()
                .id(groupChallenge.getId())
                .boardContent(groupChallenge.getBoardContent())
                .boardTitle(groupChallenge.getBoardTitle())
                .endDate(groupChallenge.getEndDate())
                .startDate(groupChallenge.getStartDate())
                .fileDTOS(toFileDTO(groupChallenge.getGroupChallengeFiles()))
                .groupChallengeReplyCount(groupChallenge.getGroupChallengeReplyCount())
                .groupChallengeStatus(groupChallenge.getGroupChallengeStatus())
                .replyCount(groupChallenge.getGroupChallengeReplyCount())
                .attendCount(groupChallenge.getGroupChallengeAttendCount())
                .createdDate(groupChallenge.getCreatedDate())
                .build();
    }

    default List<FileDTO> toFileDTO(List<GroupChallengeFile> groupChallengeFile) {
        List<FileDTO> fileDTOS = new ArrayList<>();

        groupChallengeFile.stream().forEach(
                file -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .fileType(file.getFileType())
                            .fileOriginalName(file.getFileOriginalName())
                            .filePath(file.getFilePath())
                            .fileUuid(file.getFileUuid())
                            .id(file.getId())
                            .build();
                    fileDTOS.add(fileDTO);
                }
        );
        return fileDTOS;
    }

    default GroupChallenge toGroupChallengeEntity(GroupChallengeDTO groupChallengeDTO){
        return GroupChallenge.builder()
                .id(groupChallengeDTO.getId())
                .boardTitle(groupChallengeDTO.getBoardTitle())
                .boardContent(groupChallengeDTO.getBoardContent())
                .startDate(groupChallengeDTO.getStartDate())
                .endDate(groupChallengeDTO.getEndDate())
                .groupChallengeStatus(groupChallengeDTO.getGroupChallengeStatus())
                .build();
    }


    default GroupChallengeFile toGroupChallengeFileEntity(FileDTO fileDTO){
        return GroupChallengeFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .groupChallenge(fileDTO.getGroupChallenge())
                .fileType(fileDTO.getFileType())
                .build();
    }

}
