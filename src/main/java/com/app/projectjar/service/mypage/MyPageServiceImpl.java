package com.app.projectjar.service.mypage;

import com.app.projectjar.domain.calendar.CalendarDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import com.app.projectjar.repository.diary.DiaryLikeRepository;
import com.app.projectjar.repository.diary.DiaryReplyRepository;
import com.app.projectjar.repository.diary.DiaryRepository;
import com.app.projectjar.repository.file.diary.DiaryFileRepository;
import com.app.projectjar.repository.file.member.MemberFileRepository;
import com.app.projectjar.repository.groupChallenge.GroupChallengeAttendRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.personalChallenge.ChallengeAttendRepository;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("myPage")
@Primary
@Slf4j
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;

    private final DiaryRepository diaryRepository;

    private final DiaryFileRepository diaryFileRepository;

    private final DiaryLikeRepository diaryLikeRepository;

    private final DiaryReplyRepository diaryReplyRepository;

    private final ChallengeAttendRepository challengeAttendRepository;

    private final GroupChallengeAttendRepository groupChallengeAttendRepository;

    private final MemberFileRepository memberFileRepository;

    @Override
    public void registerDiary(DiaryDTO diaryDTO, Long memberId) {
        List<FileDTO> fileDTOS = diaryDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> diaryDTO.setMemberDTO(toMemberDTO(member))
        );
        diaryRepository.save(toDiaryEntity(diaryDTO));

        if (fileDTOS != null) {
            for (int i = 0; i < fileDTOS.size(); i++) {
                if (i == 0) {
                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                } else {
                    fileDTOS.get(i).setFileType(FileType.NORMAL);
                }
                fileDTOS.get(i).setDiary(getCurrentSequence());
                diaryFileRepository.save(toDiaryFileEntity(fileDTOS.get(i)));
            }
        }

    }

    @Override
    public Diary getCurrentSequence() {
        return diaryRepository.getCurrentSequence_QueryDsl();
    }

    @Override
    public List<CalendarDTO> getCalendarDTO(Long memberId) {
        List<CalendarDTO> calendarDTOS = new ArrayList<>();

        diaryRepository.findByMemberIdDiary_QueryDsl(memberId).stream().forEach(
                diary -> {
                    CalendarDTO calendarDTO = CalendarDTO.builder()
                            .end(diary.getCreatedDate())
                            .start(diary.getCreatedDate())
                            .title(diary.getBoardTitle())
                            .id(diary.getId())
                            .build();
                    calendarDTOS.add(calendarDTO);
                }
        );
        return calendarDTOS;
    }

    @Override
    public DiaryDTO getDiary(Long diaryId) {
        return toDiaryDTO(diaryRepository.findByDiaryId_QueryDsl(diaryId).get());
    }

    @Override
    @Transactional
    public void modifyDiary(DiaryDTO diaryDTO) {
        List<FileDTO> fileDTOS = diaryDTO.getFileDTOS();

        diaryRepository.findById(diaryDTO.getId()).ifPresent(diary -> {
            Diary updatedDiary = Diary.builder()
                    .id(diary.getId())
                    .diaryStatus(diaryDTO.getDiaryStatus())
                    .member(diary.getMember())
                    .diaryLikeCount(diary.getDiaryLikeCount())
                    .diaryReplyCount(diary.getDiaryReplyCount())
                    .createDate(diary.getCreatedDate())
                    .boardContent(diaryDTO.getBoardContent())
                    .boardTitle(diaryDTO.getBoardTitle())
                    .build();

            diaryRepository.save(updatedDiary);
        });
        diaryFileRepository.deleteByDiaryId(diaryDTO.getId());
        if (fileDTOS != null) {
            for (int i = 0; i < fileDTOS.size(); i++) {
                if (i == 0) {
                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                } else {
                    fileDTOS.get(i).setFileType(FileType.NORMAL);
                }
                fileDTOS.get(i).setDiary(getCurrentSequence());
                diaryFileRepository.save(toDiaryFileEntity(fileDTOS.get(i)));
            }
        }
    }

    @Override
    @Transactional
    public void deleteDiary(Long diaryId) {
        diaryRepository.findById(diaryId).ifPresent(
                diary -> {
                    diaryFileRepository.deleteByDiaryId(diaryId);
                    diaryLikeRepository.deleteByDiaryId(diaryId);
                    diaryReplyRepository.deleteByDiaryId(diaryId);
                    diaryRepository.delete(diary);
                }
        );
    }

    @Override
    public Page<PersonalChallengeDTO> getChallengeList(String challengeStatus, Long memberId, Pageable pageable) {

        Page<ChallengeAttend> challengePageList = challengeAttendRepository.findAllWithPageAndChallenges_QueryDsl(challengeStatus, memberId, pageable);
        List<PersonalChallengeDTO> personalChallengeDTOS = challengePageList.getContent().stream().map(this::toPersonalChallengeDTO).collect(Collectors.toList());
        return new PageImpl<>(personalChallengeDTOS, challengePageList.getPageable(), challengePageList.getTotalElements());
    }

    @Override
    public Page<GroupChallengeDTO> getGroupChallengeList(String challengeStatus, Long memberId, Pageable pageable) {

        Page<GroupChallengeAttend> groupChallengeAttendList = groupChallengeAttendRepository.findAllWithPageAndGroupChallenges_QueryDsl(challengeStatus, memberId, pageable);
        List<GroupChallengeDTO> groupChallengeDTOS = groupChallengeAttendList.getContent().stream().map(this::toGroupChallengeDTO).collect(Collectors.toList());
        return new PageImpl<>(groupChallengeDTOS, groupChallengeAttendList.getPageable(), groupChallengeAttendList.getTotalElements());
    }

    @Override
    public MemberDTO getMemberDTO(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()){
            return toMemberDTO(member.get());
        }
        return null;
    }

    @Override @Transactional
    public void modifyMember(MemberDTO memberDTO) {
        FileDTO fileDTO = memberDTO.getFileDTO();


        memberRepository.findById(memberDTO.getId()).ifPresent(
                member -> {
                    if(!member.getMemberPhoneNumber().equals(memberDTO.getMemberPhoneNumber())){
                        member.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());
                    }
                    if(!member.getMemberNickname().equals(memberDTO.getMemberNickname())){
                        member.setMemberNickname(memberDTO.getMemberNickname());
                    }
                    member.setMemberName(memberDTO.getMemberName());
                    memberRepository.save(member);

                    memberFileRepository.deleteByMemberId(memberDTO.getId());
                    if (fileDTO != null) {
                        fileDTO.setFileType(FileType.REPRESENTATIVE);
                        fileDTO.setMember(member);
                        memberFileRepository.save(toMemberFileEntity(fileDTO));
                    }
                }
        );
    }

    @Override @Transactional
    public void withDrawMember(Long memberId) {
        memberRepository.withDraw_QueryDsl(memberId);
    }
}
