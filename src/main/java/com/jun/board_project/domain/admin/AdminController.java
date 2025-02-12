package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;

    @RequestMapping(value = "/admin/codeMeta", method = RequestMethod.POST)
    public String saveCodeMeta(@ModelAttribute CodeMetaForm codeMetaForm) {
        adminService.saveCodeMeta(codeMetaForm);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/function", method = RequestMethod.POST)
    public String saveFunction(@ModelAttribute FunctionForm functionForm) {
        adminService.saveFunction(functionForm);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/pointBase", method = RequestMethod.POST)
    public String savePointBase(@ModelAttribute PtBaseForm ptBaseForm) {
        adminService.savePtBase(ptBaseForm);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(ModelMap map) {
        List<CodeMetaInfo> codeMetaInfoList = adminService.findAllCodeMeta();
        List<FunctionInfo> functionInfoList = adminService.findAllFunction();
        List<PtBaseInfo> ptBaseInfoList = adminService.findAllPtBase();

        for (FunctionInfo functionInfo : functionInfoList) {
            for (CodeMetaInfo codeMetaInfo : codeMetaInfoList) {
                if (functionInfo.getDimen1().equals(codeMetaInfo.getCodeMetaId())) {
                    functionInfo.setDimen1Name(codeMetaInfo.getCodeMetaName());
                }

                if (functionInfo.getDimen2().equals(codeMetaInfo.getCodeMetaId())) {
                    functionInfo.setDimen2Name(codeMetaInfo.getCodeMetaName());
                }

                if (functionInfo.getDimen3().equals(codeMetaInfo.getCodeMetaId())) {
                    functionInfo.setDimen3Name(codeMetaInfo.getCodeMetaName());
                }

                if (functionInfo.getDimen4().equals(codeMetaInfo.getCodeMetaId())) {
                    functionInfo.setDimen4Name(codeMetaInfo.getCodeMetaName());
                }
            }
        }

        map.put("ptBaseList", ptBaseInfoList);
        map.put("functionList", functionInfoList);
        map.put("codeMetaList", codeMetaInfoList);
        return "admin/dashboard";
    }
}
