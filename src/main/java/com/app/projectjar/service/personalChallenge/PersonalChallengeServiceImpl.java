package com.app.projectjar.service.personalChallenge;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.repository.challenge.ChallengeRepository;
import com.app.projectjar.repository.file.challenge.ChallengeFileRepository;
import com.app.projectjar.repository.personalChallenge.ChallengeAttendRepository;
import com.app.projectjar.repository.personalChallenge.ChallengeReplyRepository;
import com.app.projectjar.repository.personalChallenge.PersonalChallengeRepository;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("personalChallenge") @Primary
public class PersonalChallengeServiceImpl implements PersonalChallengeService {

    private final PersonalChallengeRepository personalChallengeRepository;

    private final ChallengeRepository challengeRepository;

    private final ChallengeFileRepository challengeFileRepository;

    private final ChallengeAttendRepository challengeAttendRepository;

    private final ChallengeReplyRepository challengeReplyRepository;

    @Override
    public Page<PersonalChallengeDTO> getListByChallengeStatus(String challengeStatus, Pageable pageable) {
        Page<PersonalChallenge> personalChallenges = personalChallengeRepository.findAllByChallengeStatus(challengeStatus, pageable);

        List<PersonalChallengeDTO> personalChallengeDTOS = personalChallenges.getContent().stream()
                .map(this::toPersonalChallengeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(personalChallengeDTOS, pageable, personalChallenges.getTotalElements());
    }

    @Override
    public Page<ChallengeDTO> getAllChallengesWithPaging(int page) {
        Page<Challenge> challenges = challengeRepository.findAllWithPaging_QueryDSL(PageRequest.of(page, 10));
        List<ChallengeDTO> challengeDTOS = challenges.getContent().stream()
                .map(this::toChallengeDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(challengeDTOS, challenges.getPageable(), challenges.getTotalElements());
    }


    @Override
    public void deleteChallenges(List<Long> challengeIds) {
        for (Long challengeId : challengeIds) {
            challengeReplyRepository.deleteByChallengeId(challengeId);
            challengeFileRepository.deleteByChallengeId(challengeId);
            challengeAttendRepository.deleteByChallengeId(challengeId);
            challengeRepository.deleteById(challengeId);
        }
    }

    @Override
    public List<PersonalChallenge> getListToYesterday(LocalDate yesterday) {
        return personalChallengeRepository.findByCreateDateYesterday(yesterday);
    }

    @Override
    public void updateChallengeStatus(List<PersonalChallenge> challengeList) {
        challengeList.stream().forEach(
                personalChallenge -> {
                    personalChallenge.setChallengeStatus(ChallengeType.PRIVATE);
                    personalChallengeRepository.save(personalChallenge);
                }
        );
    }

    @Override
    public PersonalChallengeDTO getPersonalChallenge(Long personalChallengeId) {
        Optional<PersonalChallenge> personalChallenge = personalChallengeRepository.findByPersonalChallengeId(personalChallengeId);
        return toPersonalChallengeDTO(personalChallenge.get());
    }

    @Override
    public List<Challenge> getChallengeList() {
        return challengeRepository.findAll_QueryDsl();
    }

    @Override
    public void insertChallenge(Challenge challenge) {
        PersonalChallenge personalChallenge = PersonalChallenge.builder().challenge(challenge).build();
        personalChallengeRepository.save(personalChallenge);
    }

    @Override
    public Challenge getCurrentSequence() {
        return challengeRepository.getCurrentSequence_QueryDsl();
    }

    // 개인 챌린지 게시판 등록
    @Override @Transactional
    public void register(ChallengeDTO challengeDTO) {
        List<FileDTO> fileDTOS = challengeDTO.getFileDTOS();


        challengeRepository.save(toChallengeEntity(challengeDTO));
        if(fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++) {
                if(i == 0){
                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                }else {
                    fileDTOS.get(i).setFileType(FileType.NORMAL);
                }
                fileDTOS.get(i).setChallenge(getCurrentSequence());
                challengeFileRepository.save(toChallengeFileEntity(fileDTOS.get(i)));
            }
        }
    }

    @Override
    public ChallengeDTO getChallenge(Long challengeId) {
        ChallengeDTO challengeDTO = challengeRepository.findById(challengeId).map(this::toChallengeDTO).get();
        return challengeDTO;
    }

    @Override
    public void delete(Long challengeId) {
        challengeRepository.findById(challengeId).ifPresent(
                challenge -> challengeRepository.delete(challenge)
        );
    }

    @Override
    public ChallengeDTO getChallengePersonal(Long challengeId) {
        ChallengeDTO challengeDTO = challengeRepository.findByChallengeId_QueryDsl(challengeId).map(this::toChallengeDTO).get();
        return challengeDTO;
    }


    @Override @Transactional
    public void update(ChallengeDTO challengeDTO) {
        List<FileDTO> fileDTOS = challengeDTO.getFileDTOS();

        challengeFileRepository.deleteByChallengeId(challengeDTO.getId());

        challengeRepository.findById(challengeDTO.getId()).ifPresent(challenge -> {
                    Challenge updatedChallenge = challenge.builder()
                            .boardContent(challengeDTO.getBoardContent())
                            .boardTitle(challengeDTO.getBoardTitle())
                            .id(challenge.getId())
                            .createDate(challenge.getCreatedDate())
                            .build();

                    challengeRepository.save(updatedChallenge);

                    if(fileDTOS != null){
                        for (int i = 0; i < fileDTOS.size(); i++) {
                            if(i == 0){
                                fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                            }else {
                                fileDTOS.get(i).setFileType(FileType.NORMAL);
                            }
                            fileDTOS.get(i).setChallenge(updatedChallenge);
                            challengeFileRepository.save(toChallengeFileEntity(fileDTOS.get(i)));
                        }
                    }
                }
        );
    }
}
