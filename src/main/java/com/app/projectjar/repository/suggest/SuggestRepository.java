package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepository extends JpaRepository<Suggest, Long>, SuggestQueryDsl {
}
