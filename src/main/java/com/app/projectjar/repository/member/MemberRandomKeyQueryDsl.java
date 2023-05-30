package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.MemberRandomKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRandomKeyQueryDsl{
    /* 회원의 가장 최근 랜덤키 찾기 */
    public MemberRandomKey getListRandomKey(Long id);
}
