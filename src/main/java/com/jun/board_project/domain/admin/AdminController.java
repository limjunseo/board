package com.jun.board_project.domain.admin;

import com.jun.board_project.domain.member.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;

    @RequestMapping(value = "/admin/codeMeta", method = RequestMethod.POST)
    public String saveCodeMeta(@AuthenticationPrincipal MemberDetails member,
                               @ModelAttribute CodeMetaForm codeMetaForm) {

        adminService.saveCodeMeta(codeMetaForm);
        return "admin/dashboard";
    }
}
