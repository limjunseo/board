package com.jun.board_project.domain.point.pointModification;

import com.jun.board_project.domain.admin.RuleSetService;
import com.jun.board_project.domain.member.Member;
import com.jun.board_project.domain.member.MemberService;
import com.jun.board_project.domain.point.pointBase.PointBaseCd;
import com.jun.board_project.global.util.FnparamToDimens;
import com.jun.board_project.global.util.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointModificationService {
    private final PointModificationRepository pointModificationRepository;
    private final LogService logService;
    private final RuleSetService ruleSetService;
    private final MemberService memberService;

    public List<PointModificationInfo> findPointModificationInfo(LocalDateTime updateTime) {
        return pointModificationRepository.findPointModificationInfo(updateTime);
    }

    public void modifyPoint() {

        // 마지막 업데이트 시간 조회
        LocalDateTime lastUpdateTime = logService.findLastUpdateDt();

        // 수정 대상 포인트 내역 조회
        List<PointModificationInfo> pointModificationInfoList = pointModificationRepository.findPointModificationInfo(lastUpdateTime);

        // 포인트 수정
        for (PointModificationInfo pointModificationInfo : pointModificationInfoList) {
            String memberId = pointModificationInfo.getMemberId();

            //멤버 정보 조회
            Member member = memberService.findMemberById(memberId);
            PointBaseCd pointBaseCd = PointBaseCd.fromCode(pointModificationInfo.getPtBaseId());

            String [] dimensValue = FnparamToDimens.fnparamToMemberDimens(pointBaseCd.getFunctionId(), member);
            
            //지급된 포인트
            int value = pointModificationInfo.getValue();

            //현재 시점의 포인트값 조회
            int targetValue = ruleSetService.findTargetValue(pointBaseCd, dimensValue);

            if(value != targetValue){
                //포인트 수정
                pointModificationRepository.modifyPoint(pointModificationInfo.getPtModId(), targetValue);
            }




        }

    }
}
