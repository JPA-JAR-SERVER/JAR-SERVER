package com.app.projectjar.service.suggest;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestLike;
import com.app.projectjar.search.board.SuggestSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface SuggestService {

    // 저장
    public void register(SuggestDTO suggestDTO, Long memberId);
    // 그룹 목록
    public Page<SuggestDTO> getSuggestListByBoardType(String boardType, Pageable pageable);
    // 상세 보기
    public SuggestDTO getSuggest(Long suggestId);
    // 현재 시퀀스 가져오기
    public Suggest getCurrentSequence();
    // 수정
    public void update(SuggestDTO suggestDTO);
    // 삭제
    public void delete(Long suggestId);
    // 검색
    List<SuggestDTO> findSuggestWithSearch_QueryDSL(String search);



    /*관리자 페이지*/
    /*전체 조회*/
    public Page<SuggestDTO> getSuggestList(int page);

    /*메인 페이지*/
    // 페이징 없는 전체 조회
    List<SuggestDTO> findAllWithoutPaging_QueryDsl();

    // 삭제
    public void deleteSuggests(List<Long> suggestIds);

    // 마이 페이지 제안 게시 목록 조회
    public Page<SuggestDTO> getSuggestForMemberIdList(Pageable pageable, Long id);



    default SuggestDTO toSuggestDTO(Suggest suggest) {
        return SuggestDTO.builder()
                .id(suggest.getId())
                .boardTitle(suggest.getBoardTitle())
                .boardContent(suggest.getBoardContent())
                .boardType(suggest.getBoardType())
                .likeCount(suggest.getSuggestLikeCount())
                .replyCount(suggest.getSuggestReplyCount())
                .memberDTO(toMemberDTO(suggest.getMember()))
                .fileDTOS(FileToDTO(suggest.getSuggestFiles()))
                .createdDate(suggest.getCreatedDate())
                .updatedDate(suggest.getUpdatedDate())
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
                .userType(member.getUserType())
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

    default Suggest toSuggestEntity(SuggestDTO suggestDTO) {
        return Suggest.builder()
                .id(suggestDTO.getId())
                .boardTitle(suggestDTO.getBoardTitle())
                .boardContent(suggestDTO.getBoardContent())
                .boardType(suggestDTO.getBoardType())
                .member(toMemberEntity(suggestDTO.getMemberDTO()))
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

    default SuggestFile toSuggestFileEntity(FileDTO fileDTO){
        return SuggestFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .suggest(fileDTO.getSuggest())
                .fileType(fileDTO.getFileType())
                .build();
    }

    default List<SuggestFile> toSuggestFileListEntity(List<FileDTO> fileDTOS){
        List<SuggestFile> suggestFiles = new ArrayList<>();

        fileDTOS.forEach(
                fileDTO -> {
                    SuggestFile suggestFile = SuggestFile.builder()
                            .id(fileDTO.getId())
                            .fileOriginalName(fileDTO.getFileOriginalName())
                            .fileUuid(fileDTO.getFileUuid())
                            .filePath(fileDTO.getFilePath())
                            .fileType(fileDTO.getFileType())
                            .build();
                    suggestFiles.add(suggestFile);
                }
        );
        return suggestFiles;
    }

    default LikeDTO toSuggestLikeDTO(SuggestLike suggestLike){
        return LikeDTO.builder()
                .boardId(suggestLike.getSuggest().getId())
                .memberId(suggestLike.getMember().getId())
                .likeId(suggestLike.getId())
                .build();
    }

}
