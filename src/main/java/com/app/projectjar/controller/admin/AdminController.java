package com.app.projectjar.controller.admin;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.inquire.AnswerDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.inquire.Answer;
import com.app.projectjar.service.diary.DiaryService;
import com.app.projectjar.service.groupChallenge.GroupChallengeService;
import com.app.projectjar.service.inquire.AnswerService;
import com.app.projectjar.service.inquire.InquireService;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.notice.NoticeService;
import com.app.projectjar.service.personalChallenge.PersonalChallengeService;
import com.app.projectjar.service.suggest.SuggestService;
import com.app.projectjar.type.MemberType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final SuggestService suggestService;
    private final NoticeService noticeService;
    private final MemberService memberService;
    private final DiaryService diaryService;
    private final GroupChallengeService groupChallengeService;
    private final PersonalChallengeService personalChallengeService;
    private final InquireService inquireService;
    private final AnswerService answerService;
    private final MyPageService myPageService;


    @GetMapping("board/challenge/detail/{challengeId}")
    public String adminChallengeDetail(Model model, @PathVariable("challengeId") Long challengeId) {
        ChallengeDTO challengeDTO = personalChallengeService.getChallenge(challengeId);

        model.addAttribute("challengeDTO", challengeDTO);

        return "admin/board/challenge/detail";
    }
    @GetMapping("board/challenge/list")
    public String adminChallengeList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<ChallengeDTO> challengePage = personalChallengeService.getAllChallengesWithPaging(page - 1);
        model.addAttribute("pageDTO",new PageDTO(challengePage));
        model.addAttribute("challengeDTOS", challengePage.getContent());
        return "admin/board/challenge/list";
    }

    @DeleteMapping("board/challenge/delete")
    @ResponseBody
    public ResponseEntity<String> deleteChallenges(@RequestBody List<Long> challengeIds) {
        personalChallengeService.deleteChallenges(challengeIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }

    @GetMapping("board/challenge/write")
    public void adminChallengeWrite(Model model) {
        model.addAttribute("challengeDTO", new ChallengeDTO());
    }

    @PostMapping("board/challenge/write")
    public RedirectView write(@ModelAttribute("challengeDTO") ChallengeDTO challengeDTO) {

        personalChallengeService.register(challengeDTO);
        return new RedirectView("/admin/board/challenge/list");
    }

    @GetMapping("board/challenge/modify")
    public String adminChallengeModify(Model model, @RequestParam("challengeId") Long challengeId) {
        ChallengeDTO challengeDTO = personalChallengeService.getChallenge(challengeId);

        model.addAttribute("challengeDTO", challengeDTO);
        return "admin/board/challenge/modify";
    }

    @PostMapping("board/challenge/modify")
    public RedirectView adminPersonalModify(@ModelAttribute("challengeDTO") ChallengeDTO challengeDTO, @RequestParam("challengeId") Long challengeId) {


        challengeDTO.setId(challengeId);

        personalChallengeService.update(challengeDTO);

        return new RedirectView("/admin/board/challenge/detail/" + challengeId);
    }

    @PostMapping("board/challenge/modify/delete/{challengeId}")
    public RedirectView deletePersonal(@PathVariable("challengeId") Long challengeId){
        personalChallengeService.delete(challengeId);
        return new RedirectView("/admin/board/challenge/list");
    }


    @GetMapping("board/inquire/answer")
    public void adminInquireAnswer(Model model, Long inquireId) {
        InquireDTO inquireDTO = inquireService.getInquire(inquireId);
        model.addAttribute("inquireDTO", inquireDTO);
    }
    @PostMapping("board/inquire/answer")
    public String adminPostAnswer(@RequestParam("inquireId")Long inquireId, @ModelAttribute("answerDTO") AnswerDTO answerDTO) {

        answerService.register(inquireId, answerDTO);
        return "redirect:/admin/board/inquire/list";
    }


    @GetMapping("board/inquire/detail/{inquireId}")
    public String adminInquireDetail(Model model, @PathVariable("inquireId") Long inquireId) {
        InquireDTO inquireDTO = inquireService.getInquire(inquireId);
        model.addAttribute("inquireDTO", inquireDTO);

        return "admin/board/inquire/detail";
    }
    @GetMapping("board/inquire/list")
    public String adminInquireList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<InquireDTO> inquirePage = inquireService.getAllInquiresWithPaging(page - 1);
        model.addAttribute("pageDTO", new PageDTO(inquirePage));
        model.addAttribute("inquireDTOS", inquirePage.getContent());
        return "admin/board/inquire/list";
    }
    @DeleteMapping("board/inquire/delete")
    @ResponseBody
    public ResponseEntity<String> deleteInquires(@RequestBody List<Long> inquireIds) {
        inquireService.deleteInquires(inquireIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }
    @GetMapping("board/inquire/modify")
    public void adminInquireModify(){

    }

    @GetMapping("member/list")
    public String adminMemberList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
            Page<MemberDTO> memberPage = memberService.getAllMembersWithPaging(page - 1);
            model.addAttribute("pageDTO",new PageDTO(memberPage));
            model.addAttribute("memberDTOS", memberPage.getContent());
            return "admin/member/list";
    }
    @GetMapping("member/modify/{id}")
    public String adminMemberModify(Model model, @PathVariable("id") Long memberId) {
        MemberDTO memberModifyDTO = myPageService.getMemberDTO(memberId);

        model.addAttribute("memberDTO", memberModifyDTO);
        return "admin/member/modify";
    }

    @PostMapping("member/modify/{id}")
    public RedirectView adminMemberModifyPost(@PathVariable("id") Long id,
                                              @RequestParam("memberEmail") String memberEmail,
                                              @RequestParam("memberNickname") String memberNickname,
                                              @RequestParam("memberPhoneNumber") String memberPhoneNumber,
                                              @RequestParam("memberStatus") MemberType memberStatus) {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberEmail(memberEmail)
                .memberNickname(memberNickname)
                .memberPhoneNumber(memberPhoneNumber)
                .memberStatus(memberStatus)
                .build();

        memberService.updateMember(memberDTO, id);

        return new RedirectView("/admin/member/detail/" + id);
    }



    @DeleteMapping("member/delete")
    @ResponseBody
    public ResponseEntity<String> deleteMembers(@RequestBody List<Long> memberIds) {
        memberService.deleteMembers(memberIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }
    @GetMapping("member/detail/{id}")
    public String adminMemberDetail(Model model, @PathVariable("id") Long memberId) {
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);

        Long personalAttendCount = memberService.findPersonalAttendCountByMemberId(memberId);
        Long groupAttendCount = memberService.findGroupAttendCountByMemberId(memberId);

        memberDTO.setPersonalAttendCount(personalAttendCount);
        memberDTO.setGroupAttendCount(groupAttendCount);

        model.addAttribute("memberDTO", memberDTO);

        return "admin/member/detail";
    }
    @GetMapping("board/notice/detail/{noticeId}")
    public String adminNoticeDetail(Model model, @PathVariable("noticeId") Long noticeId) {
        NoticeDTO noticeDTO = noticeService.getNotice(noticeId);

        model.addAttribute("noticeDTO", noticeDTO);

        return "admin/board/notice/detail";
    }
    @GetMapping("board/notice/list")
    public String adminNoticeList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        Page<NoticeDTO> noticePage = noticeService.getAllNoticesWithPaging(page - 1);
        List<String> noticeTitles = noticePage.stream().map(NoticeDTO::getNoticeTitle).collect(Collectors.toList());
        model.addAttribute("pageDTO",new PageDTO(noticePage));
        model.addAttribute("noticeDTOS", noticePage.getContent());
        return "admin/board/notice/list";
    }
    @GetMapping("board/notice/modify/{noticeId}")
    public String adminNoticeModify(Model model, @PathVariable("noticeId") Long noticeId) {
        NoticeDTO noticeDTO = noticeService.getNotice(noticeId);

        model.addAttribute("noticeDTO", noticeDTO);
        return "admin/board/notice/modify";
    }

    @PostMapping("board/notice/modify/{noticeId}")
    public RedirectView adminNoticeModifyPost(@PathVariable Long noticeId, String noticeTitle, String noticeContent) {
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .noticeContent(noticeContent)
                .noticeTitle(noticeTitle)
                .build();

        noticeService.updateNotice(noticeId, noticeDTO);
        return new RedirectView("/admin/board/notice/list");
    }

    @DeleteMapping("board/notice/delete")
    @ResponseBody
    public ResponseEntity<String> deleteNotices(@RequestBody List<Long> noticeIds) {
        noticeService.deleteNotices(noticeIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }


    @GetMapping("board/notice/write")
    public void adminNoticeWrite(Model model) {
        model.addAttribute("noticeDTO", new NoticeDTO());
    }

    @PostMapping("board/notice/write")
    public String adminNoticeListPost(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO) {
        noticeService.register(noticeDTO);
        return "redirect:/admin/board/notice/list";
    }


    @GetMapping("board/proposal/list")
    public String adminProposalList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<SuggestDTO> suggestPage = suggestService.getSuggestList(page - 1);
        List<String> suggestTitles = suggestPage.stream().map(SuggestDTO::getBoardTitle).collect(Collectors.toList());
        model.addAttribute("pageDTO",new PageDTO(suggestPage));
        model.addAttribute("suggestDTOS", suggestPage.getContent());
        return "admin/board/proposal/list";
    }
    @GetMapping("board/proposal/detail/{suggestId}")
    public String adminProposalDetail(Model model, @PathVariable("suggestId") Long suggestId) {
        SuggestDTO suggestDTO = suggestService.getSuggest(suggestId);

        model.addAttribute("suggestDTO", suggestDTO);

        return "admin/board/proposal/detail";
    }

    @DeleteMapping("board/proposal/delete")
    @ResponseBody
    public ResponseEntity<String> deleteSuggests(@RequestBody List<Long> suggestIds) {
        suggestService.deleteSuggests(suggestIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }
    @GetMapping("board/diary/list")
    public String adminDiaryList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<DiaryDTO> diaryPage = diaryService.getAllDiarysWithPaging(page -  1);
        model.addAttribute("pageDTO",new PageDTO(diaryPage));
        model.addAttribute("diaryDTOS", diaryPage.getContent());
        return "admin/board/diary/list";
    }
    @GetMapping("board/diary/detail/{diaryId}")
    public String adminDiaryDetail(Model model, @PathVariable("diaryId") Long diaryId) {
        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);

        model.addAttribute("diaryDTO", diaryDTO);

        return "admin/board/diary/detail";
    }
    @DeleteMapping("board/diary/delete")
    @ResponseBody
    public ResponseEntity<String> deleteDiaries(@RequestBody List<Long> diaryIds) {
        diaryService.deleteDiaries(diaryIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }

    @GetMapping("board/groupChallenge/detail/{groupChallengeId}")
    public String adminGroupChallengeDetail(Model model, @PathVariable("groupChallengeId") Long groupChallengeId) {
        GroupChallengeDTO groupChallengeDTO = groupChallengeService.getGroupChallenge(groupChallengeId);

        model.addAttribute("groupChallengeDTO", groupChallengeDTO);

        return "admin/board/groupChallenge/detail";
    }
    @GetMapping("board/groupChallenge/list")
    public String adminGroupChallengeList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<GroupChallengeDTO> groupChallengePage = groupChallengeService.getAllGroupChallengesWithPaging(page - 1);
        model.addAttribute("pageDTO",new PageDTO(groupChallengePage));
        model.addAttribute("groupChallengeDTOS", groupChallengePage.getContent());
        return "admin/board/groupChallenge/list";
    }

    @DeleteMapping("board/groupChallenge/delete")
    @ResponseBody
    public ResponseEntity<String> deleteGroupChallenges(@RequestBody List<Long> groupChallengeIds) {
        groupChallengeService.deleteGroupChallenges(groupChallengeIds);
        return ResponseEntity.ok("게시물 삭제에 성공했습니다.");
    }

    @GetMapping("board/groupChallenge/modify/{groupChallengeId}")
    public String adminGroupChallengeModify(Model model, @PathVariable("groupChallengeId") Long groupChallengeId) {
        GroupChallengeDTO groupChallengeDTO = groupChallengeService.getGroupChallenge(groupChallengeId);

        model.addAttribute("groupChallengeDTO", groupChallengeDTO);
        return "admin/board/groupChallenge/modify";
    }

    @PostMapping("board/groupChallenge/modify")
    public RedirectView modify(@ModelAttribute("groupChallengeDTO") GroupChallengeDTO groupChallengeDTO, @RequestParam("groupChallengeId") Long groupChallengeId) {


        LocalDate startDate = LocalDate.parse(groupChallengeDTO.getRequestStartDate());
        LocalDate endDate = LocalDate.parse(groupChallengeDTO.getRequestEndDate());

        groupChallengeDTO.setStartDate(startDate);
        groupChallengeDTO.setEndDate(endDate);
        groupChallengeDTO.setId(groupChallengeId);

        groupChallengeService.update(groupChallengeDTO);

        return new RedirectView("/admin/board/groupChallenge/detail/" + groupChallengeId);
    }

    @GetMapping("board/groupChallenge/write")
    public void adminGroupChallengeWrite(Model model) {
        model.addAttribute("groupChallengeDTO", new GroupChallengeDTO());
    }

    @PostMapping("board/groupChallenge/write")
    public RedirectView write(@ModelAttribute("groupChallengeDTO") GroupChallengeDTO groupChallengeDTO) {
        LocalDate endDate = LocalDate.parse(groupChallengeDTO.getRequestEndDate());
        LocalDate startDate = LocalDate.parse(groupChallengeDTO.getRequestStartDate());

        groupChallengeDTO.setStartDate(startDate);
        groupChallengeDTO.setEndDate(endDate);

        groupChallengeService.register(groupChallengeDTO);
        return new RedirectView("/admin/board/groupChallenge/list");
    }

    @PostMapping("board/groupChallenge/modify/delete/{groupChallengeId}")
    public RedirectView delete(@PathVariable("groupChallengeId") Long groupChallengeId){
        groupChallengeService.delete(groupChallengeId);
        return new RedirectView("/admin/board/groupChallenge/list");
    }


}
