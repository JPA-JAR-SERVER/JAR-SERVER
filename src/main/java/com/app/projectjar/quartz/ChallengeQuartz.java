//package com.app.projectjar.quartz;
//
//
//import com.app.projectjar.entity.challenge.Challenge;
//import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
//import com.app.projectjar.service.personalChallenge.PersonalChallengeService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class ChallengeQuartz {
//    private final PersonalChallengeService personalChallengeService;
//
//    private final int COUNT = 12;
//
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void challengeUpdate() {
//
//        Random random = new Random();
//        // 오늘 날짜
//        LocalDate now = LocalDate.now();
//        // 어제 날짜
//        LocalDate yesterday = now.minusDays(1);
//        // 어제 날짜의 챌린지 조회
//        List<PersonalChallenge> listToYesterday = personalChallengeService.getListToYesterday(yesterday);
//        // 어제날짜의 챌린지의 challengeStatus를 PRIVATE로 업데이트
//        personalChallengeService.updateChallengeStatus(listToYesterday);
//        // 관리자에서 등록한 개인챌린지 전체 목록
//        List<Challenge> challengeList = personalChallengeService.getChallengeList();
//        // 어제 나온 챌린지인지 검사 후 담을 객체
//        List<Challenge> filterChallengeList  = new ArrayList<>();
//
//
//        for (int i = 0; i < challengeList.size(); i++) {
//            boolean check = false;
//            for (int j = 0; j < listToYesterday.size(); j++) {
//                if(challengeList.get(i).getId() == listToYesterday.get(j).getChallenge().getId()){
//                    check = true;
//                    break;
//                }
//            }
//            //어제날짜의 챌린지의 아이디 값이 다른 것만 넣어준다.
//            if(!check){
//                filterChallengeList.add(challengeList.get(i));
//            }
//        }
//
//        // 랜덤하게 요소를 랜덤화
//        Collections.shuffle(filterChallengeList, random);
//        for (int i = 0; i < COUNT; i++) {
//            Challenge challenge = filterChallengeList.get(i);
//            personalChallengeService.insertChallenge(challenge);
//        }
//
//    }
//}
