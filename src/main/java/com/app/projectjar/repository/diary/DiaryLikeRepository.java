package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.DiaryLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, Long>, DiaryQueryLikeDsl {

}
