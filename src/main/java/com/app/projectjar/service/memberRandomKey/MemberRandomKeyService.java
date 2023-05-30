package com.app.projectjar.service.memberRandomKey;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.MemberRandomKey;

public interface MemberRandomKeyService {

    /* 랜덤키 생성 */
    public MemberRandomKey saveRandomKey(Member member);

    /* 가장 최근 랜덤키 불러오기 */
    public MemberRandomKey getLatestRandomKey(Long id);
}
