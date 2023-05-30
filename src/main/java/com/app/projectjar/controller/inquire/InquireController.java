package com.app.projectjar.controller.inquire;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.inquire.InquireDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.inquire.InquireService;
import com.app.projectjar.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("board/inquire/*")
@RequiredArgsConstructor
@Slf4j
public class InquireController {
    private final InquireService inquireService;
    private final MyPageService myPageService;

    @GetMapping("detail/{inquireId}")
    public String inquireDetail(Model model, @PathVariable("inquireId") Long inquireId, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        InquireDTO inquireDTO = inquireService.getInquire(inquireId);
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("inquireDTO", inquireDTO);

        return "board/inquire/detail";
    }

    @GetMapping("list")
    public String getAllInquires(Model model, @RequestParam(value = "page", defaultValue = "1") int page, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        Page<InquireDTO> inquirePage = inquireService.getAllInquiresWithPaging(page - 1);
        List<String> inquiresTitle = inquirePage.stream().map(InquireDTO::getInquireTitle).collect(Collectors.toList());
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("pageDTO", new PageDTO(inquirePage));
        model.addAttribute("inquireDTOS", inquirePage.getContent());

        return "board/inquire/list";
    }

    @GetMapping("write")
    public void goToWriteForm(Model model, InquireDTO inquireDTO, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO",memberDTO);
    }

    @PostMapping("write")
    public RedirectView write(@ModelAttribute("inquireDTO") InquireDTO inquireDTO, @AuthenticationPrincipal UserDetail userDetail) {

        Long id = userDetail.getId();
        inquireService.register(inquireDTO, id);
        return new RedirectView("/board/inquire/list");
    }

    @GetMapping("answer")
    public void adminInquireAnswer(Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO",memberDTO);
    }

}