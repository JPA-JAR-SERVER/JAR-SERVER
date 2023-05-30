//package com.app.projectjar.quartz;
//
//import com.app.projectjar.domain.file.FileDTO;
//import com.app.projectjar.service.file.FileService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class FileCheckQuartz {
//    private final FileService fileService;
//
//    /*
//     *   0 * * * * * : 매 분 0초마다
//     *   0/1 * * * * : 매 1초 간격
//     *   0 0/1 * * * : 매 1분 간격
//     *   0 0/5 * ? : 매 5분 간격
//     *   0 0 0/1 * * : 매 1시간 간격
//     *   0 0 0 * * ? : 매일 0시 마다
//     *   0 0 0 1 * ? : 매월 1일 마다
//     *   * 10-13 * * * * : 매 10, 11, 12, 13분에 동작한다.
//     * */
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void checkFiles() {
//        List<FileDTO> fileDTOS = fileService.getFileList(LocalDateTime.now().minusDays(1));
//
//        List<Path> fileDTO_Paths = fileDTOS.stream().map(fileDTO -> Paths.get("C:/upload/" + getPathFromYesterday(),fileDTO.getFileUuid() + "_" + fileDTO.getFileOriginalName()))
//                .collect(Collectors.toList());
//
//
//        File directory = Paths.get("C:/upload",getPathFromYesterday()).toFile();
//        Arrays.stream(directory.listFiles(file -> !fileDTO_Paths.contains(file.toPath()))).forEach(File::delete);
//    }
//
//    private String getPathFromYesterday(){
//        LocalDate yesterday = LocalDate.now();
//        yesterday = yesterday.minusDays(1);
//
//        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(yesterday);
//    }
//}
