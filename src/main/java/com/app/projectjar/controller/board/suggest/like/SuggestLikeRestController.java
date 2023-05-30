package com.app.projectjar.controller.board.suggest.like;

import com.app.projectjar.domain.like.LikeDTO;
import com.app.projectjar.service.suggest.like.SuggestLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suggest/heart/*")
public class SuggestLikeRestController {
    private final SuggestLikeService suggestLikeService;

    // 좋아요 카운트 1 증가
    @PostMapping("heartUp")
    public void likeInsert(@RequestBody LikeDTO likeDTO) {
        suggestLikeService.heartUp(likeDTO);
    }

    // 좋아요 카운트 1 감소
    @DeleteMapping("heartDown")
    public void likeDown(@RequestBody LikeDTO likeDTO){
        suggestLikeService.heartDown(likeDTO);
    }

    // 각 게시판 별 좋아요 갯수
    @GetMapping("heartCount")
    public Integer getLikeCount(@RequestParam("boardId") Long boardId){
        return suggestLikeService.getHeartCount(boardId);
    }

    // 좋아요를 누른 상태인지 확인
    @PostMapping("heart-check")
    public Boolean likeCheck(@RequestBody LikeDTO likeDTO){
        return suggestLikeService.heartCheck(likeDTO);
    }

}
