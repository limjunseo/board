package com.jun.board_project.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TopMemberHisService {
    private final TopMemberHisRepository topMemberHisRepository;

    public void saveTopMemberHis() {
        topMemberHisRepository.calTopMember();
    }
}
