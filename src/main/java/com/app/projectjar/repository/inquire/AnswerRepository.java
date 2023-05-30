package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerQueryDsl {
}
