package com.app.projectjar.controller.board.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.service.diary.DiaryService;
import com.app.projectjar.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/diary/*")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryService diaryService;
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
    public Slice<DiaryDTO> getDiaryList(@RequestParam("sort") String sort, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 12);
        return diaryService.getOpenDiaryList(sort, pageRequest);
    }

    @GetMapping("detail/{diaryId}")
    public String goToDetail(Model model, @PathVariable("diaryId") Long diaryId, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);

        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("diaryDTO", diaryDTO);
        return "board/diary/detail";
    }

    @GetMapping("modify/{diaryId}")
    public String goToModify(Model model, @PathVariable("diaryId") Long diaryId, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("diaryDTO", diaryDTO);
        return "board/diary/modify";
    }

    @PostMapping("modify")
    public RedirectView modify(@RequestParam("boardId") Long boardId, @ModelAttribute("diaryDTO") DiaryDTO diaryDTO) {

        diaryDTO.setId(boardId);
        diaryService.modifyDiary(diaryDTO);
        if(diaryDTO.getDiaryStatus().equals("PRIVATE")){
            return new RedirectView("/board/diary/detail/" + boardId);
        }else {
            return new RedirectView("/board/diary/list");
        }
    }
}
