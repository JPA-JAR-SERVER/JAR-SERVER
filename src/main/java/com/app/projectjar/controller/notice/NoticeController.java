package com.app.projectjar.controller.notice;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.domain.page.PageDTO;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("board/notice/*")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final MyPageService myPageService;

    @GetMapping("detail/{noticeId}")
    public String noticeDetail(Model model, @PathVariable("noticeId") Long noticeId, HttpSession session) {
        NoticeDTO noticeDTO = noticeService.getNotice(noticeId);
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("noticeDTO", noticeDTO);

        return "board/notice/detail";
    }

    @GetMapping("list")
    public String getAllNotices(Model model, @RequestParam(value="page", defaultValue="1") int page, HttpSession session) {
        Page<NoticeDTO> noticePage = noticeService.getAllNoticesWithPaging(page - 1);
        List<String> noticeTitles = noticePage.stream().map(NoticeDTO::getNoticeTitle).collect(Collectors.toList());
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("pageDTO",new PageDTO(noticePage));
        model.addAttribute("noticeDTOS", noticePage.getContent());
        return "board/notice/list";
    }

}
