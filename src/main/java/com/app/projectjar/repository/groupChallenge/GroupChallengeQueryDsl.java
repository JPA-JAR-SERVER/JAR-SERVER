package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.search.board.GroupChallengeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GroupChallengeQueryDsl {

    // 목록
    public Page<GroupChallenge> findAllGroupChallengeWithPaging_QueryDsl(Pageable pageable);

    // 전체 조회
    public Page<GroupChallenge> findAllWithPaging_QueryDSL(Pageable pageable);

    // 캘린더
    public List<GroupChallenge> findAllCalendar();

    // 종료된 챌린지
    public Page<GroupChallenge> findAllGroupChallengeByPrivateWithPaging_QueryDsl(Pageable pageable);

    // 상세 보기
    public Optional<GroupChallenge> findByGroupChallengeId_QueryDsl(Long groupChallengeId);

    // 현재 시퀀스 가져오기
    public GroupChallenge getCurrentSequence_QueryDsl();

    // 목록(페이징 처리 없는 버전)
    public List<GroupChallenge> findAllGroupChallenge_QueryDsl();

    // 검색
    public List<GroupChallenge> findGroupChallengeWithSearch_QueryDSL(String search);
    
    // 시작 날짜가 오늘 날짜 가져오기
    public List<GroupChallenge> findByStartDate(LocalDate startDate);

    // 어제 날짜가 종료인 날짜 그룹 챌린지 목록 가져오기
    public List<GroupChallenge> findByEndDate(LocalDate endDate);
}
