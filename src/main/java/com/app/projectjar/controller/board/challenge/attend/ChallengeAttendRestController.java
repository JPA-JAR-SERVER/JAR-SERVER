package com.app.projectjar.controller.board.challenge.attend;

import com.app.projectjar.service.personalChallenge.attend.ChallengeAttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge/attend/*")
public class ChallengeAttendRestController {
    private final ChallengeAttendService challengeAttendService;

    @PostMapping("insert")
    public void insert(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        challengeAttendService.insertAttend(boardId,memberId);
    }

    @GetMapping("check")
    public Boolean attendCheck(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId) {
        return challengeAttendService.attendCheck(boardId, memberId);
    }

    @DeleteMapping("delete")
    public void deleteAttend(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        challengeAttendService.deleteAttend(boardId, memberId);
    }

    @PatchMapping("update")
    public void updateAttendStatus(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId) {
        challengeAttendService.updateAttendToAttendType(boardId, memberId);
    }

    @GetMapping("success-check")
    public Boolean challengeSuccessCheck(@RequestParam("boardId")Long boardId, @RequestParam("memberId") Long memberId){
        return challengeAttendService.challengeSuccessCheck(boardId, memberId);
    }

}
