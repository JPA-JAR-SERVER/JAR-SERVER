package com.app.projectjar.controller.board.groupChallenge.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.service.groupChallenge.reply.GroupChallengeReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupChallenge/reply/*")
@RequiredArgsConstructor
public class GroupChallengeReplyRestController {
    private final GroupChallengeReplyService groupChallengeReplyService;

    @PostMapping("save")
    public void saveReply(@RequestBody ReplyRequestDTO replyRequestDTO) {
        groupChallengeReplyService.insertReply(replyRequestDTO);
    }

    @DeleteMapping("delete")
    public void deleteReply(@RequestParam("replyId") Long replyId) {
        groupChallengeReplyService.deleteReply(replyId);
    }

    @PatchMapping("update")
    public void updateReply(@RequestParam("replyId") Long replyId, @RequestParam("replyContent") String replyContent) {
        groupChallengeReplyService.modifyReply(replyId, replyContent);
    }

    @GetMapping("list")
    public Slice<ReplyDTO> getList(@RequestParam("boardId") Long groupChallengeId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 5);
        return groupChallengeReplyService.getReplyList(groupChallengeId, pageable);
    }

}
