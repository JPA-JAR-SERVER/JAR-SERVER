package com.app.projectjar.repository.file.member;

import com.app.projectjar.entity.file.member.MemberFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFileRepository extends JpaRepository<MemberFile, Long>, MemberFileQueryDsl {
}
