package com.app.projectjar.service.memberRandomKey;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.MemberRandomKey;
import com.app.projectjar.repository.member.MemberRandomKeyRepository;
import com.app.projectjar.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("memberRandomKey") @Primary
@Slf4j
public class MemberRandomKeyServiceImpl implements MemberRandomKeyService {
    private final MemberRepository memberRepository;
    private final MemberRandomKeyRepository randomKeyRepository;

    @Override
    public MemberRandomKey saveRandomKey(Member member) {
        MemberRandomKey memberRandomKey = new MemberRandomKey(member);
        randomKeyRepository.save(memberRandomKey);
        return memberRandomKey;
    }

    @Override
    public MemberRandomKey getLatestRandomKey(Long id) {
        return randomKeyRepository.getListRandomKey(id);
    }
}
