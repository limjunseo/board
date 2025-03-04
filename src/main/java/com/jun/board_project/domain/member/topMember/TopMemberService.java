package com.jun.board_project.domain.member.topMember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopMemberService {
    private final TopMemberRepository topMemberRepository;

    public void saveTopMemberHis() {
        topMemberRepository.calTopMember();
    }

    public List<TopMemberInfo> findYesterdayTopMember() {
        return topMemberRepository.findYesterdayTopMember();
    }

    public List<MonthTopMemberInfo> findThisMonthTopMember() {
        return topMemberRepository.findThisMonthTopMember();
    }
}
