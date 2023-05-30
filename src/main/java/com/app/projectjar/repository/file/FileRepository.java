package com.app.projectjar.repository.file;

import com.app.projectjar.entity.file.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long>, FileQueryDsl {
}
