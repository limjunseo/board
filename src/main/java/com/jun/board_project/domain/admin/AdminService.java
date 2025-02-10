package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public void saveCodeMeta(CodeMetaForm codeMetaForm) {
        adminRepository.saveCodeMeta(codeMetaForm);
    }
}
