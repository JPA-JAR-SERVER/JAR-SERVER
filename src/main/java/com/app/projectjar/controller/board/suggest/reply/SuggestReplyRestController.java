package com.app.projectjar.controller.board.suggest.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.service.suggest.reply.SuggestReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggest/reply/*")
@RequiredArgsConstructor
@Slf4j
public class SuggestReplyRestController {
    private final SuggestReplyService suggestReplyService;

    @PostMapping("save")
    public void saveReply(@RequestBody ReplyRequestDTO replyRequestDTO) {
        suggestReplyService.insertReply(replyRequestDTO);
    }

    @DeleteMapping("delete")
    public void deleteReply(@RequestParam("replyId") Long replyId) {
        suggestReplyService.deleteReply(replyId);
    }

    @PatchMapping("update")
    public void updateReply(@RequestParam("replyId") Long replyId, @RequestParam("replyContent") String replyContent) {
        suggestReplyService.modifyReply(replyId, replyContent);
    }

    @GetMapping("list")
    public Slice<ReplyDTO> getList(@RequestParam("boardId") Long suggestId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 5);
        return suggestReplyService.getReplyList(suggestId, pageable);
    }

}
