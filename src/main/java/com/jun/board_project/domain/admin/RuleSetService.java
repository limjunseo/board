package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class RuleSetService {
    private final RuleSetRepository ruleSetRepository;
    private final RuleMatrixService ruleMatrixService;


    public int findTargetValue(int ptBaseId, int functionId, String[] diemens, Timestamp targetDt) {
        int cellId = ruleMatrixService.findCellId(ptBaseId, diemens); //규칙매트릭스의 cellId 조회
        return ruleSetRepository.findTargetValue(ptBaseId, functionId, cellId, targetDt);
    }

}
