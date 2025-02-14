package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RuleMatrixService {
    private final RuleMatrixRepository ruleMatrixRepository;

    public int findCellId(int functionId, String [] dimens) {
        return ruleMatrixRepository.findCellId(functionId, dimens);
    }
}
