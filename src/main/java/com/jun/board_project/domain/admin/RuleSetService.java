package com.jun.board_project.domain.admin;

import com.jun.board_project.domain.point.pointBase.PointBaseCd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RuleSetService {
    private final RuleSetRepository ruleSetRepository;
    private final RuleMatrixService ruleMatrixService;


    public int findTargetValue(PointBaseCd pointBaseCd, String[] diemens) {
        int cellId = ruleMatrixService.findCellId(pointBaseCd.getCode(), diemens); //규칙매트릭스의 cellId 조회
        return ruleSetRepository.findTargetValue(pointBaseCd.getCode(), pointBaseCd.getFunctionId(), cellId, "99991231");
    }

}
