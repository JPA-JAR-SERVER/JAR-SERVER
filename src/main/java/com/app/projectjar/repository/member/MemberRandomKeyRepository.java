package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.MemberRandomKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRandomKeyRepository  extends JpaRepository<MemberRandomKey, Long> , MemberRandomKeyQueryDsl{
}
