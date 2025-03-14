package com.jun.board_project.domain.member.topMember;

import com.jun.board_project.domain.member.topMember.dto.MonthTopMemberInfo;
import com.jun.board_project.domain.member.topMember.dto.TopMemberInfo;
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
