package com.app.projectjar.controller.board.suggest;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/suggest/*")
@RequiredArgsConstructor
@Slf4j
public class SuggestController {
    private final SuggestService suggestService;
    private final MyPageService myPageService;

    @GetMapping("write")
    public void goToWriteForm(Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO",memberDTO);
    }

    @PostMapping("write")
    public RedirectView write(@ModelAttribute("suggestDTO") SuggestDTO suggestDTO, HttpSession session) {

        Long memberId = ((MemberDTO) session.getAttribute("member")).getId();
        suggestService.register(suggestDTO, memberId);
        return new RedirectView("/board/suggest/list");
    }

    @GetMapping("list")
    public void goToList(Model model, HttpSession session){
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("list-content")
    @ResponseBody
    public Page<SuggestDTO> getList(@RequestParam("boardType") String boardType, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 12);
        return suggestService.getSuggestListByBoardType(boardType, pageRequest);
    }

    @GetMapping("detail/{boardId}")
    public String goToDetail(Model model, @PathVariable("boardId") Long boardId, HttpSession session) {
        SuggestDTO suggestDTO = suggestService.getSuggest(boardId);
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("suggestDTO", suggestDTO);
        return "board/suggest/detail";
    }

    @GetMapping("modify/{boardId}")
    public String goToModify(Model model, @PathVariable("boardId") Long boardId,HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        SuggestDTO suggestDTO = suggestService.getSuggest(boardId);

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("suggestDTO", suggestDTO);
        return "board/suggest/modify";
    }

    @PostMapping("modify")
    public RedirectView modify(@RequestParam("boardId") Long boardId, @ModelAttribute("suggestDTO") SuggestDTO suggestDTO) {

        suggestDTO.setId(boardId);
        suggestService.update(suggestDTO);
        return new RedirectView("/board/suggest/detail/" + boardId);
    }

    @PostMapping("delete/{boardId}")
    public RedirectView delete(@PathVariable("boardId") Long boardId){
        suggestService.delete(boardId);
        return new RedirectView("/board/suggest/list");
    }

}
