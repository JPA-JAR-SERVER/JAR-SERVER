package com.app.projectjar.controller.main;

import com.app.projectjar.domain.board.BoardSearchDTO;
import com.app.projectjar.domain.calendar.GroupCalendarDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.search.board.GroupChallengeSearch;
import com.app.projectjar.search.board.SuggestSearch;
import com.app.projectjar.service.groupChallenge.GroupChallengeService;
import com.app.projectjar.service.groupChallenge.reply.GroupChallengeReplyService;
import com.app.projectjar.service.member.MemberService;
import com.app.projectjar.service.mypage.MyPageService;
import com.app.projectjar.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final SuggestService suggestService;

    private final GroupChallengeService groupChallengeService;

    private final GroupChallengeReplyService groupChallengeReplyService;

    private final HttpSession session;

    private final MemberService memberService;

    private final MyPageService myPageService;

    @GetMapping("")
    public void main(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        Long memberId = null;
        if (session.getAttribute("member") == null) {
            if(userDetail != null){
                Member member = memberService.getOptionalMember(userDetail.getId()).orElseGet(null);
                MemberDTO memberDTO = memberService.toMemberDTO(member);
                session.setAttribute("member", memberDTO);
            }
        } else {
            memberId = ((MemberDTO)session.getAttribute("member")).getId();
            MemberDTO memberDTO = memberService.getMember(memberId);
            session.setAttribute("member", memberDTO);
        }

        List<GroupChallengeDTO> groupChallengeDTOS = groupChallengeService.getGroupChallengeList(PageRequest.of(0, 6)).getContent();
        List<GroupCalendarDTO> calendarDTOS = groupChallengeService.findAllCalendar();
        MemberDTO memberInfo = null;
        if (userDetail != null) {
            memberInfo = myPageService.getMemberDTO(userDetail.getId());
        } else if (memberId != null){
            memberInfo = myPageService.getMemberDTO(memberId);
        }
        model.addAttribute("memberDTO", memberInfo);
        model.addAttribute("groupChallengeDTOS", groupChallengeDTOS);
        model.addAttribute("calendarDTOS", calendarDTOS);
    }

    @GetMapping("/list-content")
    @ResponseBody
    public List<SuggestDTO> listContent() {
        // suggestService를 사용하여 리스트를 가져오는 로직 작성
        List<SuggestDTO> list = suggestService.findAllWithoutPaging_QueryDsl(); // 적절한 로직으로 대체해야 합니다.
        return list;
    }

    @GetMapping("search")
    public void boardSearch(@RequestParam String search, Model model, HttpSession session) {
        List<GroupChallengeDTO> groupSearch = groupChallengeService.findBoardSearch(search);
        List<SuggestDTO> suggestSearch = suggestService.findSuggestWithSearch_QueryDSL(search);
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if (member != null) {
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("groupChallengeDTOS", groupSearch);
        model.addAttribute("suggestDTOS", suggestSearch);

    }

    @GetMapping("service-introduction")
    public void serviceIntroduce(Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if (member != null) {
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("faq")
    public void faq(Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if (member != null) {
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("terms")
    public String goToTerms(Model model, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if (member != null) {
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }
        model.addAttribute("memberDTO", memberDTO);

        return "terms/terms-of-service";
    }

}