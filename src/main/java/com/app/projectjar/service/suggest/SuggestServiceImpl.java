package com.app.projectjar.service.suggest;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.repository.file.suggest.SuggestFileRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import com.app.projectjar.search.board.SuggestSearch;
import com.app.projectjar.type.FileType;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("suggest") @Primary
@Slf4j
public class SuggestServiceImpl implements SuggestService {
    private final SuggestRepository suggestRepository;

    private final SuggestFileRepository suggestFileRepository;

    private final MemberRepository memberRepository;

    private final SuggestReplyRepository suggestReplyRepository;


    // 제안 게시판 등록
    @Override @Transactional
    public void register(SuggestDTO suggestDTO, Long memberId) {
        List<FileDTO> fileDTOS = suggestDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> suggestDTO.setMemberDTO(toMemberDTO(member))
        );

        suggestRepository.save(toSuggestEntity(suggestDTO));
        if(fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++) {
                if(i == 0){
                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                }else {
                    fileDTOS.get(i).setFileType(FileType.NORMAL);
                }
                fileDTOS.get(i).setSuggest(getCurrentSequence());
                suggestFileRepository.save(toSuggestFileEntity(fileDTOS.get(i)));
            }
        }
    }

//    제안 게시판 목록
    @Override
    public Page<SuggestDTO> getSuggestListByBoardType(String boardType, Pageable pageable) {
        Page<Suggest> suggests = suggestRepository.findByBoardTypeWithPaging_QueryDsl(boardType, pageable);
        List<SuggestDTO> suggestDTOS = suggests.getContent().stream()
                .map(this::toSuggestDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(suggestDTOS, suggests.getPageable(), suggests.getTotalElements());
    }

    //    상세보기
    @Override
    public SuggestDTO getSuggest(Long suggestId) {
        Optional<Suggest> suggest = suggestRepository.findByIdSuggest_QueryDsl(suggestId);
        return toSuggestDTO(suggest.get());
    }

//    현재 시퀀스 가져오기
    @Override
    public Suggest getCurrentSequence() {
        return suggestRepository.getCurrentSequence_QueryDsl();
    }

    @Override @Transactional
    public void update(SuggestDTO suggestDTO) {
        List<FileDTO> fileDTOS = suggestDTO.getFileDTOS();

        suggestFileRepository.deleteBySuggestId(suggestDTO.getId());

        suggestRepository.findById(suggestDTO.getId()).ifPresent(suggest -> {
            Suggest updatedSuggest = Suggest.builder()
                    .id(suggest.getId())
                    .boardType(suggestDTO.getBoardType())
                    .boardContent(suggestDTO.getBoardContent())
                    .boardTitle(suggestDTO.getBoardTitle())
                    .member(suggest.getMember())
                    .createDate(suggest.getCreatedDate())
                    .suggestLikeCount(suggest.getSuggestLikeCount())
                    .suggestReplyCount(suggest.getSuggestReplyCount())
                    .build();

            suggestRepository.save(updatedSuggest);

            if(fileDTOS != null){
                for (int i = 0; i < fileDTOS.size(); i++) {
                    if(i == fileDTOS.size() - 1){
                        fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                    }else {
                        fileDTOS.get(i).setFileType(FileType.NORMAL);
                    }
                    fileDTOS.get(i).setSuggest(updatedSuggest);
                    suggestFileRepository.save(toSuggestFileEntity(fileDTOS.get(i)));
                }
            }
        });



    }

    @Override @Transactional
    public void delete(Long suggestId) {
        suggestRepository.findById(suggestId).ifPresent(
                suggest -> {
                    suggestReplyRepository.deleteBySuggestId(suggestId);
                    suggestRepository.delete(suggest);
                }
        );
    }

    @Override
    public Page<SuggestDTO> getSuggestList(int page) {
        Page<Suggest> suggests = suggestRepository.findAllWithPaging_QueryDsl(PageRequest.of(page, 10));
        List<SuggestDTO> suggestDTOS = suggests.getContent().stream()
                .map(this::toSuggestDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(suggestDTOS, suggests.getPageable(), suggests.getTotalElements());
    }

    @Override
    public List<SuggestDTO> findAllWithoutPaging_QueryDsl() {
        List<Suggest> foundSuggests = suggestRepository.findAllWithoutPaging_QueryDsl();

        return foundSuggests.stream()
                .map(this::toSuggestDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSuggests(List<Long> suggestIds) {
        for (Long suggestId : suggestIds) {
            suggestRepository.deleteById(suggestId);
        }
    }

    @Override
    public Page<SuggestDTO> getSuggestForMemberIdList(Pageable pageable, Long id) {
        Page<Suggest> suggests = suggestRepository.findAllByMemberIdWithPaging_QueryDsl(pageable, id);
        List<SuggestDTO> suggestDTOS = suggests.stream().map(this::toSuggestDTO).collect(Collectors.toList());
        return new PageImpl<>(suggestDTOS, suggests.getPageable(), suggests.getTotalElements());
    }

    @Override
    public List<SuggestDTO> findSuggestWithSearch_QueryDSL(String search) {
        List<Suggest> suggests = suggestRepository.findSuggestWithSearch_QueryDSL(search);
        return suggests.stream()
                .map(this::toSuggestDTO)
                .collect(Collectors.toList());
    }
}
