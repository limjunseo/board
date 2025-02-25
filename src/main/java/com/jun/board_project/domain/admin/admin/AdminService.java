package com.jun.board_project.domain.admin.admin;

import com.jun.board_project.domain.admin.codeMeta.CodeMetaForm;
import com.jun.board_project.domain.admin.codeMeta.CodeMetaInfo;
import com.jun.board_project.domain.admin.function.FunctionForm;
import com.jun.board_project.domain.admin.function.FunctionInfo;
import com.jun.board_project.domain.admin.pointBase.PtBaseForm;
import com.jun.board_project.domain.admin.pointBase.PtBaseInfo;
import com.jun.board_project.domain.admin.ruleMatrix.RuleMatrixForm;
import com.jun.board_project.domain.admin.ruleMatrix.RuleMatrixInfo;
import com.jun.board_project.domain.admin.ruleSet.RuleSetForm;
import com.jun.board_project.domain.admin.ruleSet.RuleSetInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public void saveCodeMeta(CodeMetaForm codeMetaForm) {
        adminRepository.saveCodeMeta(codeMetaForm);
    }

    public void saveFunction(FunctionForm functionForm) {
        adminRepository.saveFunction(functionForm);
    }

    public void saveRuleSet(RuleSetForm ruleSetForm) {
        adminRepository.saveRuleSet(ruleSetForm);
    }

    public void saveRuleMatrix(RuleMatrixForm ruleMatrixForm) {
        adminRepository.saveRuleMatrix(ruleMatrixForm);
    }

    public void savePtBase(PtBaseForm ptBaseForm) {
        adminRepository.savePtBase(ptBaseForm);
    }

    public FunctionInfo findFunction(int ptBaseId) {
        return adminRepository.findFunction(ptBaseId);
    }


    public String findCodeMetaName(String codeMetaId) {
        return adminRepository.findCodeMetaName(codeMetaId);
    }

    public PtBaseInfo findPtBase(int ptBaseId) {
        return adminRepository.findPtBase(ptBaseId);
    }

    public List<RuleSetInfo> findRuleSet(int ptBaseId) {
        return adminRepository.findRuleSetByPtBaseId(ptBaseId);
    }

    public List<RuleMatrixInfo> findAllRuleMatrixByFunctionId(int functionId) {
        return adminRepository.findAllRuleMatrixByFunctionId(functionId);
    }

    public List<CodeMetaInfo> findAllCodeMeta() {
        return adminRepository.findAllCodeMeta();
    }

    public List<FunctionInfo> findAllFunction() {
        return adminRepository.findAllFunction();
    }

    public List<PtBaseInfo> findAllPtBase() {
        return adminRepository.findAllPtBase();
    }
}
