//package com.app.projectjar.quartz;
//
//import com.app.projectjar.entity.groupChallenge.GroupChallenge;
//import com.app.projectjar.service.groupChallenge.GroupChallengeService;
//import com.app.projectjar.type.GroupChallengeType;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class GroupChallengeQuartz {
//    private final GroupChallengeService groupChallengeService;
//
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void updateGroupChallengeBoardType() {
//        // 오늘 날짜
//        LocalDate now = LocalDate.now();
//        // 어제 날짜
//        LocalDate yesterday = now.minusDays(1);
//
//        // 오늘이 시작 날짜인 게시물 목록
//        List<GroupChallenge> startGroupChallengeList = groupChallengeService.getListByStartDate(now);
//        groupChallengeService.updateGroupChallengeTypeToOpen(startGroupChallengeList);
//
//        // 어제 날짜가 시작 날짜인 게시물 목록
//        List<GroupChallenge> endGroupChallengeList = groupChallengeService.getListByEndDate(yesterday);
//        groupChallengeService.updateGroupChallengeTypeToPrivate(endGroupChallengeList);
//    }
//}
