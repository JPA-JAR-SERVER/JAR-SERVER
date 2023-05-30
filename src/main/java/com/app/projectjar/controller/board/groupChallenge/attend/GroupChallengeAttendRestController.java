package com.app.projectjar.controller.board.groupChallenge.attend;

import com.app.projectjar.service.groupChallenge.attend.GroupChallengeAttendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupChallenge/attend/*")
@RequiredArgsConstructor
@Slf4j
public class GroupChallengeAttendRestController {

    private final GroupChallengeAttendService groupChallengeAttendService;

    @PostMapping("insert")
    public void insert(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        groupChallengeAttendService.insertAttend(boardId,memberId);
    }

    @GetMapping("check")
    public Boolean attendCheck(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId) {
        return groupChallengeAttendService.attendCheck(boardId, memberId);
    }

    @DeleteMapping("delete")
    public void deleteAttend(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        groupChallengeAttendService.deleteAttend(boardId, memberId);
    }

    @PatchMapping("update")
    public void updateAttendStatus(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId) {
        groupChallengeAttendService.updateAttendToAttendType(boardId, memberId);
    }

    @GetMapping("success-check")
    public Boolean challengeSuccessCheck(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        return groupChallengeAttendService.challengeSuccessCheck(boardId, memberId);
    }
}
