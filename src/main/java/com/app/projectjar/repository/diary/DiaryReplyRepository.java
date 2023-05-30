package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.DiaryReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryReplyRepository extends JpaRepository<DiaryReply, Long>, DiaryReplyQueryDsl {
}
