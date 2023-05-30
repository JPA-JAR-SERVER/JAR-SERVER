package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Inquire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquireRepository extends JpaRepository<Inquire, Long>, InquireQueryDsl {
}
