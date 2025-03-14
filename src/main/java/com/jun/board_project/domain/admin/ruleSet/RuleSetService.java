package com.jun.board_project.domain.admin.ruleSet;

import com.jun.board_project.domain.admin.ruleMatrix.RuleMatrixService;
import com.jun.board_project.domain.point.pointBase.PointBaseCd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RuleSetService {
    private final RuleSetRepository ruleSetRepository;
    private final RuleMatrixService ruleMatrixService;


    //현재 시점의 타겟값 조회
    public int findTargetValue(PointBaseCd pointBaseCd, String[] diemens) {
        int cellId = ruleMatrixService.findCellId(pointBaseCd.getCode(), diemens); //규칙매트릭스의 cellId 조회
        return ruleSetRepository.findTargetValue(pointBaseCd.getCode(), pointBaseCd.getFunctionId(), cellId, "99991231");
    }

    //특정 시점의 타겟값 조회
    public int findTargetValue(PointBaseCd pointBaseCd, String[] diemens, String targetDate) {
        int cellId = ruleMatrixService.findCellId(pointBaseCd.getCode(), diemens); //규칙매트릭스의 cellId 조회
        return ruleSetRepository.findTargetValue(pointBaseCd.getCode(), pointBaseCd.getFunctionId(), cellId, targetDate);
    }


}
