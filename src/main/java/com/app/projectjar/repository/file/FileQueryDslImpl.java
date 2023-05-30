package com.app.projectjar.repository.file;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.file.QFileDTO;
import com.app.projectjar.entity.file.QFiles;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.app.projectjar.entity.file.QFiles.files;

@RequiredArgsConstructor
public class FileQueryDslImpl implements FileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<FileDTO> findAllFiles(LocalDateTime date) {
        return query.select(new QFileDTO(files.id,files.fileOriginalName, files.fileUuid, files.filePath))
                .from(files)
                .where(files.filePath.eq(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))))
                .fetch();
    }
}
