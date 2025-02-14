package com.jun.board_project.domain.memberLoginHis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberLoginHisService {
    private final MemberLoginHisRepository memberLoginHisRepository;

    public void saveMemberLoginHis(MemberLoginHis memberLoginHis) {
        memberLoginHisRepository.save(memberLoginHis);
    }

    public String isFirstLogin(String memberId) {
        return memberLoginHisRepository.isFirstLogin(memberId);
    }
}
