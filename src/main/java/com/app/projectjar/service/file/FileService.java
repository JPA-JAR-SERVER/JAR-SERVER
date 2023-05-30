package com.app.projectjar.service.file;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;


    public List<FileDTO> getFileList(LocalDateTime date){
        return fileRepository.findAllFiles(date);
    }
}
