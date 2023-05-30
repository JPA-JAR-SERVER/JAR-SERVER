package com.app.projectjar.service.member;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MailDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface MemberService extends UserDetailsService {
//    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

//    회원정보 조회
    public MemberDTO getMemberInfo(Long id);

//    이메일 중복 검사
    public Long checkEmail(String memberEmail);

//    휴대폰 번호 중복 검사
    public Long checkPhoneNumber(String memberPhoneNumber);

//    닉네임 중복 검사
    public Long checkNickName(String memberNickName);

//   비밀번호 찾기
    public Member getMemberEmail(String memberEmail);

//    비밀 번호 변경
    public void updatePassword(Long id, String memberPassword, PasswordEncoder passwordEncoder);

//    회원정보 수정
    public void updateMember(MemberDTO memberDTO, Long id);

//    ID로 회원정보 조회
    public MemberDTO getMember(Long id);

    public Optional<Member> getOptionalMember(Long id);

//    뱃지 조회
    public int getMemberBadgeCount(Long id);

//    뱃지 업데이트
    public void updateBadge(Long id);

//    인증 번호 발급
    public void checkSMS(String memberPhone, String code);

    /* 랜덤키로 계정 찾기 */
    public Member findMemberByRandomKey(String randomKey);

    /* 랜덤키로 계정 찾기 */
    public Member findMemberByMemberEmailAndRandomKey(String memberEmail, String randomKey);

    /* 메일보내기 */
    public void sendMail(MailDTO mail);

    Long findPersonalAttendCountByMemberId(Long id);

    Long findGroupAttendCountByMemberId(Long id);

    default Member memberDTOToEntity(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .memberType(memberDTO.getMemberType())
                .userType(memberDTO.getUserType())
//                .memberFile(memberDTO.getFileDTO())
                .build();
    }

    /*관리자 페이지*/
//    회원 전체 조회
    public Page<MemberDTO> getAllMembersWithPaging(int page);
    // 삭제
    public void deleteMembers(List<Long> memberIds);

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .createdDate(member.getCreatedDate())
                .userType(member.getUserType())
                .memberType(member.getMemberType())
                .build();
    }

    default MemberFile toMemberFileDTO(MemberFile memberFile){
        return MemberFile.builder()
                .id(memberFile.getId())
                .fileOriginalName(memberFile.getFileOriginalName())
                .fileUuid(memberFile.getFileUuid())
                .filePath(memberFile.getFilePath())
                .fileType(memberFile.getFileType())
                .build();
    }


    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .memberType(memberDTO.getMemberType())
                .userType(memberDTO.getUserType())
                .build();
    }

    default MemberFile toMemberFileEntity(FileDTO fileDTO){
        return MemberFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .fileType(fileDTO.getFileType())
                .build();
    }
}
