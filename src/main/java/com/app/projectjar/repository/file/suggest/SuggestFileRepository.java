package com.app.projectjar.repository.file.suggest;

import com.app.projectjar.entity.file.suggest.SuggestFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestFileRepository extends JpaRepository<SuggestFile, Long>, SuggestFIleQueryDsl {

}
