package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @RequestMapping(value = "/admin/ruleSet", method = RequestMethod.GET)
    public List<RuleSetInfo> getRuleSetInfo(@RequestParam int ptBaseId ) {
        List<RuleSetInfo> ruleSetInfoList = adminService.findRuleSet(ptBaseId);
        String [] dimenId = new String[4];
        String [] dimenName = new String[4];

        for (RuleSetInfo ruleSetInfo : ruleSetInfoList) {
            dimenId[0] = ruleSetInfo.getDimen1();
            dimenId[1] = ruleSetInfo.getDimen2();
            dimenId[2] = ruleSetInfo.getDimen3();
            dimenId[3] = ruleSetInfo.getDimen4();
        }

        for (int i = 0; i < 4; i++) {
            dimenName[i] = adminService.findCodeMetaName(dimenId[i]);
        }

        for (RuleSetInfo ruleSetInfo : ruleSetInfoList) {
            ruleSetInfo.setDimen1Name(dimenName[0]);
            ruleSetInfo.setDimen2Name(dimenName[1]);
            ruleSetInfo.setDimen3Name(dimenName[2]);
            ruleSetInfo.setDimen4Name(dimenName[3]);
            System.out.println(ruleSetInfo);
        }


        return ruleSetInfoList;
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
