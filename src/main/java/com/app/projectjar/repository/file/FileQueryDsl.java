package com.app.projectjar.repository.file;

import com.app.projectjar.domain.file.FileDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface FileQueryDsl {

    public List<FileDTO> findAllFiles(LocalDateTime date);
}
