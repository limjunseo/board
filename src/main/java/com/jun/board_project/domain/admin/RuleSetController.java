package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("ptBaseInfo", ptBaseInfo);
        model.addAttribute("functionInfo", functionInfo);
        if(ruleSetInfoList.size() == 0) {
            return "admin/ruleSetPage";
        }

        String [] dimenId = new String[4];
        String [] dimenName = new String[4];



        model.addAttribute("ruleSetList", ruleSetInfoList);

        return "admin/ruleSetPage";
    }

    @RequestMapping(value = "/admin/ruleSet", method = RequestMethod.POST)
    public ResponseEntity<Void> saveRuleSet(@RequestBody RuleMatrixForm ruleMatrixForm,
                                            @RequestBody RuleSetForm ruleSetForm) {

        adminService.saveRuleSet(ruleSetForm);
        adminService.saveRuleMatrix(ruleMatrixForm);
        return ResponseEntity.ok().build();
    }


}
