package com.app.projectjar.controller.mypage;

import com.app.projectjar.domain.calendar.CalendarDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.diary.DiaryLikeDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.domain.suggest.SuggestLikeDTO;
import com.app.projectjar.service.diary.DiaryService;
import com.app.projectjar.service.diary.like.DiaryLikeService;
import com.app.projectjar.service.inquire.InquireService;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.suggest.SuggestService;
import com.app.projectjar.service.suggest.like.SuggestLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MyPageService myPageService;
    private final MemberService memberService;
    private final InquireService inquireService;
    private final SuggestService suggestService;
    private final DiaryService diaryService;
    private final SuggestLikeService suggestLikeService;
    private final DiaryLikeService diaryLikeService;

    private final HttpSession session;

    @GetMapping("main")
    public void main(Model model){

        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        List<CalendarDTO> calendarDTOS = myPageService.getCalendarDTO(memberId);
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("calendarDTOS",calendarDTOS);
    }

    @PostMapping("register")
    public RedirectView register(@ModelAttribute("diaryDTO") DiaryDTO diaryDTO, HttpSession session, Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        myPageService.registerDiary(diaryDTO, memberId);
        return new RedirectView("/mypage/main?check=true");
    }

    @GetMapping("diary-detail")
    @ResponseBody
    public DiaryDTO getDiaryDTO(@RequestParam("boardId")Long diaryId) {
        return myPageService.getDiary(diaryId);
    }

    @PostMapping("diary-modify")
    public RedirectView modifyDiary(@RequestParam("boardId") Long diaryId, @ModelAttribute("diaryDTO") DiaryDTO diaryDTO) {
        diaryDTO.setId(diaryId);
        myPageService.modifyDiary(diaryDTO);
        return new RedirectView("/mypage/main");
    }

    @PostMapping("diary-delete")
    public RedirectView deleteDiary(@RequestParam("boardId") Long diaryId){
        myPageService.deleteDiary(diaryId);
        return new RedirectView("/mypage/main");
    }

    @GetMapping("badge")
    public void badge(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);

        model.addAttribute("memberDTO", memberDTO);
        int totalCount = memberService.getMemberBadgeCount(memberId);
        model.addAttribute("totalCount", totalCount);
    }

    @DeleteMapping("delete-main/{boardId}")
    @ResponseBody
    public void deleteMain(@PathVariable("boarId") Long diaryId){ diaryService.delete(diaryId);}

    @GetMapping("personal-challenge")
    public void personal(Model model, HttpSession session){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("challenge-content")
    @ResponseBody
    public Page<PersonalChallengeDTO> getPersonalChallengeDTOS(@RequestParam("challengeStatus") String challengeStatus, @RequestParam("memberId") Long memberId, @RequestParam("page") int page){
        PageRequest pageRequest = PageRequest.of(page, 9);
        return myPageService.getChallengeList(challengeStatus, memberId, pageRequest);
    }

    @GetMapping("group-challenge")
    public void group(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("group-challenge-content")
    @ResponseBody
    public Page<GroupChallengeDTO> getGroupChallengeDTOS(@RequestParam("challengeStatus") String challengeStatus, @RequestParam("memberId") Long memberId, @RequestParam("page") int page){
        PageRequest pageRequest = PageRequest.of(page, 9);
        return myPageService.getGroupChallengeList(challengeStatus, memberId, pageRequest);
    }

    @GetMapping("inquire")
    public void goToInquire(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("inquire-list")
    @ResponseBody
    public Page<InquireDTO> inquire(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<InquireDTO> inquireDTOS = inquireService.getInquireForMemberIdList(pageable, id);
        return inquireDTOS;
    }

    @DeleteMapping("delete-inquire/{boardId}")
    @ResponseBody
    public void deleteInquire(@PathVariable("boardId") Long boardId){
        inquireService.deleteInquire(boardId);
    }

    @GetMapping("propsal")
    public void goToPropsal(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("propsal-list")
    @ResponseBody
    public Page<SuggestDTO> propsal(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<SuggestDTO> suggestDTOS = suggestService.getSuggestForMemberIdList(pageable, id);
        return suggestDTOS;
    }

    @DeleteMapping("delete-suggest/{boardId}")
    @ResponseBody
    public void deletePropsal(@PathVariable("boardId") Long boardId){
        suggestService.delete(boardId);
    }


    @GetMapping("share")
    public void goToShare(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("share-list")
    @ResponseBody
    public Page<DiaryDTO> share(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<DiaryDTO> diaryDTOS = diaryService.getDiaryForMemberIdList(pageable, id);
        return diaryDTOS;
    }

    @DeleteMapping("delete-diary/{boardId}")
    @ResponseBody
    public void deleteShare(@PathVariable("boardId") Long boardId){
        diaryService.delete(boardId);
    }

    @GetMapping("suggest-like-list")
    public void goToSuggestLike(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("suggest-like-list-content")
    @ResponseBody
    public Page<SuggestLikeDTO> suggestLikeList(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<SuggestLikeDTO> suggestLikeDTOS = suggestLikeService.getLikeSuggestForMemberIdList(pageable, id);
        return suggestLikeDTOS;
    }

    @GetMapping("diary-like-list")
    public void goToDiaryLike(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("diary-like-list-content")
    @ResponseBody
    public Page<DiaryLikeDTO> diaryLikeList(@RequestParam("memberId") Long id, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 6);
        Page<DiaryLikeDTO> diaryLikeDTOS = diaryLikeService.getLikeDiaryForMemberIdList(pageable, id);
        return diaryLikeDTOS;
    }


    @GetMapping("modify")
    public void modify(Model model){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        MemberDTO memberDTO = myPageService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);
    }

    @PostMapping("modify-member")
    public RedirectView modifyMember(@ModelAttribute("memberDTO") MemberDTO memberDTO){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        memberDTO.setId(memberId);
        myPageService.modifyMember(memberDTO);
        return new RedirectView("/mypage/main");
    }

    @PostMapping("withdraw")
    public RedirectView withdraw(){
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        myPageService.withDrawMember(memberId);
        return new RedirectView("/member/logout");
    }

}
