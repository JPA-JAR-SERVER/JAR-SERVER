package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long>, DiaryQueryDsl {
}
