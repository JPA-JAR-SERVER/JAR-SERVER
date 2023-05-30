package com.app.projectjar.controller.board.groupChallenge;

import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.service.groupChallenge.GroupChallengeService;
import com.app.projectjar.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/challenge/group/*")
@RequiredArgsConstructor
@Slf4j
public class GroupChallengeController {

    private final GroupChallengeService groupChallengeService;
    private final MyPageService myPageService;


    @GetMapping("content-list")
    @ResponseBody
    public Page<GroupChallengeDTO> getGroupChallengeList(@RequestParam("boardStatus") String boardStatus, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 9);
        if(boardStatus.equals("OPEN")){
            return groupChallengeService.getGroupChallengeList(pageable);
        }else {
            return groupChallengeService.getEndGroupChallengeList(pageable);
        }
    }

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

    @GetMapping("detail/{boardId}")
    public String goToDetail(Model model,  @PathVariable("boardId") Long boardId, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        MemberDTO memberDTO = null;
        if(member != null){
            Long memberId = member.getId();
            memberDTO = myPageService.getMemberDTO(memberId);
        }
        GroupChallengeDTO groupChallengeDTO = groupChallengeService.getGroupChallenge(boardId);

        model.addAttribute("groupChallengeDTO", groupChallengeDTO);
        model.addAttribute("memberDTO",memberDTO);
        return "board/challenge/group/detail";
    }
}
