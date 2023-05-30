package com.app.projectjar.service.notice;

import com.app.projectjar.domain.notice.NoticeDTO;
import com.app.projectjar.entity.notice.Notice;
import com.app.projectjar.repository.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("notice") @Primary
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Page<NoticeDTO> getAllNoticesWithPaging(int page) {
        Page<Notice> notices = noticeRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<NoticeDTO> noticeDTOS = notices.getContent().stream()
                .map(this::toNoticeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(noticeDTOS, notices.getPageable(), notices.getTotalElements());
    }


    @Override
    public void register(NoticeDTO noticeDTO) {
        noticeRepository.save(toNoticeEntity(noticeDTO));
    }

    @Override
    public NoticeDTO getNotice(Long noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        NoticeDTO noticeDTO = toNoticeDTO(notice.get());

        // 생성 날짜를 가져와서 noticeDTO에 설정
        noticeDTO.setCreatedDate(notice.get().getCreatedDate());

        return noticeDTO;
    }


//    @Override
//    public void deleteNotice(Long noticeId) {
//        noticeRepository.deleteById(noticeId);
//    }

    @Override
    public void deleteNotices(List<Long> noticeIds) {
        for (Long noticeId : noticeIds) {
            noticeRepository.deleteById(noticeId);
        }
    }

    @Override
    public void updateNotice(Long noticeId, NoticeDTO noticeDTO) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);

        optionalNotice.ifPresent(
                notice -> {
                    notice.setNoticeContent(noticeDTO.getNoticeContent());
                    notice.setNoticeTitle(noticeDTO.getNoticeTitle());
                    noticeRepository.save(notice);
                }
        );


//        if (optionalNotice.isPresent()) {
//            Notice existingNotice = optionalNotice.get();
//            existingNotice.setNoticeTitle(noticeDTO.getNoticeTitle());
//            existingNotice.setNoticeContent(noticeDTO.getNoticeContent());
//            noticeRepository.save(existingNotice);
//        } else {
//        }
    }

//    @Override
//    public void setNotice(NoticeDTO noticeDTO) {
//        Notice notice = toNoticeEntity(noticeDTO);
//
//        // 기존 작성 날짜 가져오기
//        Notice existingNotice = noticeRepository.findById(notice.getId()).orElseThrow(() -> new IllegalArgumentException("Notice not found"));
//        LocalDateTime createdDate = existingNotice.getCreatedDate();
//
////        // 기존 작성 날짜 및 업데이트 날짜 설정
////        notice.setCreatedDate(createdDate);
////        notice.setUpdatedDate(LocalDateTime.now());
//
//        noticeRepository.save(notice);
//    }


}
