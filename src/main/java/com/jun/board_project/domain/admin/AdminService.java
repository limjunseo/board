package com.jun.board_project.domain.admin;

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

    public void savePtBase(PtBaseForm ptBaseForm) {
        adminRepository.savePtBase(ptBaseForm);
    }

    public List<CodeMetaInfo> findCodeMeta() {
        return adminRepository.findCodeMeta();
    }

    public List<FunctionInfo> findAllFunction() {
        return adminRepository.findAllFunction();
    }
}
