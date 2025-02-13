package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RuleSetController {
    private final AdminService adminService;

    @RequestMapping(value = "/admin/ruleSetPage", method = RequestMethod.GET)
    public String getRuleSetInfo(@RequestParam("ptBaseId") int ptBaseId, Model model) {
        PtBaseInfo ptBaseInfo = adminService.findPtBase(ptBaseId);
        FunctionInfo functionInfo = adminService.findFunction(ptBaseInfo.getFunctionId());
        List<RuleSetInfo> ruleSetInfoList = adminService.findRuleSet(ptBaseId);
        List<RuleMatrixInfo> ruleMatrixInfoList = adminService.findAllRuleMatrixByFunctionId(ptBaseInfo.getFunctionId());


        model.addAttribute("ptBaseInfo", ptBaseInfo);
        model.addAttribute("functionInfo", functionInfo);
        if(ruleSetInfoList.size() == 0) {
            return "admin/ruleSetPage";
        }

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
        }

        model.addAttribute("ruleMatrixList", ruleMatrixInfoList);
        model.addAttribute("ruleSetList", ruleSetInfoList);

        return "admin/ruleSetPage";
    }


}
