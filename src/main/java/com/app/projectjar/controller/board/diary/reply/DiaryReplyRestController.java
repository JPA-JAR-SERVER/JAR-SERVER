package com.app.projectjar.controller.board.diary.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.service.diary.reply.DiaryReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary/reply/*")
@RequiredArgsConstructor
public class DiaryReplyRestController {
    private final DiaryReplyService diaryReplyService;

    @PostMapping("save")
    public void saveReply(@RequestBody ReplyRequestDTO replyRequestDTO) {
        diaryReplyService.insertReply(replyRequestDTO);
    }

    @DeleteMapping("delete")
    public void deleteReply(@RequestParam("replyId") Long replyId) {
        diaryReplyService.deleteReply(replyId);
    }

    @PatchMapping("update")
    public void updateReply(@RequestParam("replyId") Long replyId, @RequestParam("replyContent") String replyContent) {
        diaryReplyService.modifyReply(replyId, replyContent);
    }

    @GetMapping("list")
    public Slice<ReplyDTO> getList(@RequestParam("boardId") Long diaryId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 5);
        return diaryReplyService.getReplyList(diaryId, pageable);
    }

}
