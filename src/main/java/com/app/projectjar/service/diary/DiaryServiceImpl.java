package com.app.projectjar.service.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.repository.diary.DiaryReplyRepository;
import com.app.projectjar.repository.diary.DiaryRepository;
import com.app.projectjar.repository.file.diary.DiaryFileRepository;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("diary") @Primary
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    private final DiaryFileRepository diaryFileRepository;

    private final DiaryReplyRepository diaryReplyRepository;

    @Override
    public Page<DiaryDTO> getAllDiarysWithPaging(int page) {
        Page<Diary> diaries = diaryRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<DiaryDTO> diaryDTOS = diaries.getContent().stream()
                .map(this::toDiaryDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(diaryDTOS, diaries.getPageable(), diaries.getTotalElements());
    }

    @Override
    public Slice<DiaryDTO> getOpenDiaryList(String sort, Pageable pageable) {
        Slice<Diary> diaryList = diaryRepository.findByMemberIdDiary_QueryDsl(sort, pageable);

        List<DiaryDTO> diaryDTOS = diaryList.getContent().stream().map(this::toDiaryDTO).collect(Collectors.toList());
        return new SliceImpl<>(diaryDTOS, pageable, diaryList.hasNext());
    }

    @Override
    public DiaryDTO getDiary(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findByDiaryId_QueryDsl(diaryId);
        return toDiaryDTO(diary.get());
    }

    @Override
    public Page<DiaryDTO> getDiaryForMemberIdList(Pageable pageable, Long id) {
        Page<Diary> diaries = diaryRepository.findAllByMemberWithPaging_QueryDsl(pageable, id);
        List<DiaryDTO> diaryDTOS = diaries.stream().map(this::toDiaryDTO).collect(Collectors.toList());
        return new PageImpl<>(diaryDTOS, diaries.getPageable(), diaries.getTotalElements());
    }

    @Override @Transactional
    public void modifyDiary(DiaryDTO diaryDTO) {
        List<FileDTO> fileDTOS = diaryDTO.getFileDTOS();

        diaryFileRepository.deleteByDiaryId(diaryDTO.getId());

        diaryRepository.findById(diaryDTO.getId()).ifPresent(
                diary -> {
                    Diary updateDiary = Diary.builder()
                            .id(diary.getId())
                            .boardContent(diaryDTO.getBoardContent())
                            .boardTitle(diaryDTO.getBoardTitle())
                            .member(diary.getMember())
                            .diaryStatus(diaryDTO.getDiaryStatus())
                            .createDate(diary.getCreatedDate())
                            .diaryReplyCount(diary.getDiaryReplyCount())
                            .diaryLikeCount(diary.getDiaryLikeCount())
                            .build();
                    diaryRepository.save(updateDiary);

                    if(fileDTOS != null){
                        for (int i = 0; i < fileDTOS.size(); i++) {
                            if(i == 0){
                                fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                            }else {
                                fileDTOS.get(i).setFileType(FileType.NORMAL);
                            }
                            fileDTOS.get(i).setDiary(updateDiary);
                            diaryFileRepository.save(toDiaryFileEntity(fileDTOS.get(i)));
                        }
                    }
                }
        );

    }

    @Override @Transactional
    public void delete(Long diaryId) {
       diaryRepository.findById(diaryId).ifPresent(
               diary -> {
                   diaryReplyRepository.deleteByDiaryId(diaryId);
                   diaryRepository.deleteByDiaryId_QueryDsl(diaryId);
               }
       );
    }

    @Override
    public Diary getCurrentSequence() {
        return diaryRepository.getCurrentSequence_QueryDsl();
    }

    @Override
    public void deleteDiaries(List<Long> diaryIds) {
        for (Long diaryId : diaryIds) {
            diaryFileRepository.deleteByDiaryId(diaryId);
            diaryReplyRepository.deleteByDiaryId(diaryId);
            diaryRepository.deleteByDiaryId_QueryDsl(diaryId);
        }
    }
}
