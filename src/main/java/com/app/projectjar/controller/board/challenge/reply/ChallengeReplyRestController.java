package com.app.projectjar.controller.board.challenge.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.service.personalChallenge.reply.ChallengeReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge/reply/*")
public class ChallengeReplyRestController {

    private final ChallengeReplyService challengeReplyService;

    @PostMapping("save")
    public void saveReply(@RequestBody ReplyRequestDTO replyRequestDTO) {
        challengeReplyService.insertReply(replyRequestDTO);
    }

    @DeleteMapping("delete")
    public void deleteReply(@RequestParam("replyId") Long replyId) {
        challengeReplyService.deleteReply(replyId);
    }

    @PatchMapping("update")
    public void updateReply(@RequestParam("replyId") Long replyId, @RequestParam("replyContent") String replyContent) {
        challengeReplyService.modifyReply(replyId, replyContent);
    }

    @GetMapping("list")
    public Slice<ReplyDTO> getList(@RequestParam("boardId") Long groupChallengeId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 5);
        return challengeReplyService.getReplyList(groupChallengeId, pageable);
    }

}
