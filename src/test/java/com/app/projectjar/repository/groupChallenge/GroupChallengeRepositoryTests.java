//package com.app.projectjar.repository.groupChallenge;
//
//import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
//import com.app.projectjar.entity.groupChallenge.GroupChallenge;
//import com.app.projectjar.repository.file.groupChallenge.GroupChallengeFileRepository;
//import com.app.projectjar.type.GroupChallengeType;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static com.app.projectjar.type.FileType.REPRESENTATIVE;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class GroupChallengeRepositoryTests {
//
//    @Autowired
//    private GroupChallengeRepository groupChallengeRepository;
//
//    @Autowired
//    private GroupChallengeFileRepository groupChallengeFileRepository;
//
//    @Test
//    public void saveTest() {
//        for (int i = 0; i < 5; i++) {
//            groupChallengeRepository.save(new GroupChallenge(
//                    "그룹 챌린지 제목" + (i + 1),
//                    "그룹 챌린지 내용" + (i + 1),
//                    GroupChallengeType.WAIT,
//                    LocalDate.of(2023,05,16),
//                    LocalDate.of(2023,05,20)));
//        }
//    }
//
//    @Test
//    public void fileSaveTest() {
//        groupChallengeRepository.findById(231L).ifPresent(
//                groupChallenge ->
//                        groupChallengeFileRepository.save(new GroupChallengeFile(
//                                "테스트12312.png",
//                                UUID.randomUUID().toString(),
//                                "2023/05/08",
//                                REPRESENTATIVE,
//                                groupChallenge
//                        ))
//        );
//    }
//
//    @Test
//    public void combinedTest() {
//        for (int i = 0; i < 10; i++) {
//            GroupChallenge groupChallenge = new GroupChallenge(
//                    "그룹 챌린지 제목" + (i + 1),
//                    "그룹 챌린지 내용" + (i + 1),
//                    GroupChallengeType.PRIVATE,
//                    LocalDate.of(2023, 05, 8 + i),
//                    LocalDate.of(2023, 05, 10 + i)
//            );
//
//            groupChallengeRepository.save(groupChallenge);
//
//            groupChallengeFileRepository.save(new GroupChallengeFile(
//                    "테스트12312.png",
//                    UUID.randomUUID().toString(),
//                    "2023/05/08",
//                    REPRESENTATIVE,
//                    groupChallenge
//            ));
//        }
//    }
//
//
//    @Test
//    public void findAllGroupChallengeWithPaging_QueryDslTest() {
//        groupChallengeRepository.findAllGroupChallengeWithPaging_QueryDsl(PageRequest.of(0 , 10)).map(GroupChallenge::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findByGroupChallengeId_QueryDslTest() {
//        groupChallengeRepository.findByGroupChallengeId_QueryDsl(231L).map(GroupChallenge::toString).ifPresent(log::info);
//    }
//
//    @Test
//    public void findAllGroupChallenge_QueryDslTest() {
//        groupChallengeRepository.findAllGroupChallenge_QueryDsl().stream().map(GroupChallenge::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findByStartDateTest(){
//        groupChallengeRepository.findByStartDate(LocalDate.now()).stream().map(GroupChallenge::toString).forEach(log::info);
//    }
//
//}
