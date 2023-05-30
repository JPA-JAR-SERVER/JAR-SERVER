package com.app.projectjar.service.inquire;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InquireService {
    //    전체 목록 페이징
    public Page<InquireDTO> getAllInquiresWithPaging(int page);

    // 저장
    public void register(InquireDTO InquireDTO, Long id);

    // 상세 보기
    public InquireDTO getInquire(Long inquireId);

    // 삭제
    public void deleteInquire(Long inquireId);

    // 업데이트
    public void updateInquire(Long inquireId, InquireDTO inquireDTO);

    // 마이 페이지 문의 사항 목록 조회
    public Page<InquireDTO> getInquireForMemberIdList(Pageable pageable, Long id);

    public void deleteInquires(List<Long> inquireIds);

//    public void insertAnswer(AnswerDTO answerDTO);



    default Inquire toInquireEntity(InquireDTO inquireDTO) {
        return Inquire.builder()
                .id(inquireDTO.getId())
                .inquireTitle(inquireDTO.getInquireTitle())
                .inquireContent(inquireDTO.getInquireContent())
                .member(toMember(inquireDTO.getMemberDTO()))
                .build();
    }

    default InquireDTO toInquireDTO(Inquire inquire) {
        return InquireDTO.builder()
                .id(inquire.getId())
                .answerDTO(toAnswerDTO(inquire.getAnswer()))
                .answerType(inquire.getAnswerType())
                .createdDate(inquire.getCreatedDate())
                .updatedDate(inquire.getUpdatedDate())
                .inquireContent(inquire.getInquireContent())
                .inquireTitle(inquire.getInquireTitle())
                .memberDTO(toMemberDTO(inquire.getMember()))
                .build();
    }

    default AnswerDTO toAnswerDTO (Answer answer){
        if(answer == null){
            return null;
        }else {
            return AnswerDTO.builder()
                    .answerContent(answer.getAnswerContent())
                    .registerDate(answer.getCreatedDate())
                    .build();
        }

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

    default Member toMember(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .badgeType(memberDTO.getBadgeType())
//                .memberFile(memberDTO.getFileDTO())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberStatus(memberDTO.getMemberStatus())
                .memberType(memberDTO.getMemberType())
                .build();
    }



}
