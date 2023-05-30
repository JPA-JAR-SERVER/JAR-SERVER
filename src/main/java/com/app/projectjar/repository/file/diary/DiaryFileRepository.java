package com.app.projectjar.repository.file.diary;

import com.app.projectjar.entity.file.diary.DiaryFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryFileRepository extends JpaRepository<DiaryFile, Long>, DiaryFileQueryDsl {
}
