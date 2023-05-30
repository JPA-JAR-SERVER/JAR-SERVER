package com.app.projectjar.service.notice;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.entity.notice.Notice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService {
//    전체 목록 페이징
    public Page<NoticeDTO> getAllNoticesWithPaging(int page);

    // 저장
    public void register(NoticeDTO noticeDTO);

    // 상세 보기
    public NoticeDTO getNotice(Long noticeId);

    // 삭제
     public void deleteNotices(List<Long> noticeIds);

    // 업데이트
    public void updateNotice(Long noticeId, NoticeDTO noticeDTO);

//    public void setNotice(NoticeDTO noticeDTO);


    default Notice toNoticeEntity(NoticeDTO noticeDTO) {
        return Notice.builder()
                .id(noticeDTO.getId())
                .noticeTitle(noticeDTO.getNoticeTitle())
                .noticeContent(noticeDTO.getNoticeContent())
                .build();
    }

    default NoticeDTO toNoticeDTO(Notice notice) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .createdDate(notice.getCreatedDate())
                .updatedDate(notice.getUpdatedDate())
                .build();
    }
}
