package com.app.projectjar.controller.board.diary.like;

import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.service.diary.like.DiaryLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary/heart/*")
@RequiredArgsConstructor
public class DiaryLikeRestController {

    private final DiaryLikeService diaryLikeService;

    // 좋아요 카운트 1 증가
    @PostMapping("heartUp")
    public void likeInsert(@RequestBody LikeDTO likeDTO) {
        diaryLikeService.heartUp(likeDTO);
    }

    // 좋아요 카운트 1 감소
    @DeleteMapping("heartDown")
    public void likeDown(@RequestBody LikeDTO likeDTO){
        diaryLikeService.heartDown(likeDTO);
    }

    // 각 게시판 별 좋아요 갯수
    @GetMapping("heartCount")
    public Integer getLikeCount(@RequestParam("boardId") Long diaryId){
        return diaryLikeService.getHeartCount(diaryId);
    }

    // 좋아요를 누른 상태인지 확인
    @PostMapping("heart-check")
    public Boolean likeCheck(@RequestBody LikeDTO likeDTO){
        return diaryLikeService.heartCheck(likeDTO);
    }
}
