package com.app.projectjar.service.groupChallenge.attend;

public interface GroupChallengeAttendService {

    // 참여 하기
    public void insertAttend(Long boardId,Long memberId);

    // 참여 취소
    public void deleteAttend(Long boardId, Long memberId);

    // 참여한 챌린지 인지 검사
    public Boolean attendCheck(Long boardId, Long memberId);

    // 도전 완료한 챌린지인지 검사
    public Boolean challengeSuccessCheck(Long boardId, Long memberId);

    // 도전 완료
    public void updateAttendToAttendType(Long boardId, Long memberId);

    // 참여 갯수
    public Integer getAttendCount(Long boardId);
}
