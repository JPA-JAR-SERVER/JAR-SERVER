package com.app.projectjar.controller.board.challenge;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.personalChallenge.PersonalChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/challenge/personal/*")
@RequiredArgsConstructor
public class ChallengeController {
    private final PersonalChallengeService personalChallengeService;
    private final MyPageService myPageService;

    @GetMapping("list")
    public void goToList(Model model, HttpSession session){
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO",memberDTO);
    }

    @GetMapping("list-content")
    @ResponseBody
    public Page<PersonalChallengeDTO> getList(@RequestParam("challengeStatus") String challengeStatus, @RequestParam(defaultValue = "0", name = "page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 12);
        return personalChallengeService.getListByChallengeStatus(challengeStatus, pageRequest);
    }


    @GetMapping("detail/{personalChallengeId}")
    public String goToDetail(Model model, @PathVariable("personalChallengeId") Long personalChallengeId,HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        PersonalChallengeDTO personalChallengeDTO = personalChallengeService.getPersonalChallenge(personalChallengeId);
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("personalChallengeDTO", personalChallengeDTO);
        return "board/challenge/personal/detail";
    }

}
