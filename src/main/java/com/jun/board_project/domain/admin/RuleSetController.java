package com.jun.board_project.domain.admin;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.digester.RuleSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Void> addRuleSetAndMatrix(@RequestBody Map<String, Object> requestData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // ruleSet JSON 처리
        Map<String, Object> ruleSetData = (Map<String, Object>) requestData.get("ruleSet");
        System.out.println(ruleSetData);

        RuleSetForm ruleSetForm = new RuleSetForm();
        ruleSetForm.setPtBaseId(Integer.parseInt((String) ruleSetData.get("ptBaseId")));
        ruleSetForm.setFunctionId(Integer.parseInt((String) ruleSetData.get("functionId")));
        ruleSetForm.setCellId(Integer.parseInt((String) ruleSetData.get("cellId")));
        ruleSetForm.setTargetValue(Integer.parseInt((String) ruleSetData.get("targetValue")));

        ruleSetForm.setEndDt("99991231");
        ruleSetForm.setStartDt(LocalDate.now().format(formatter));

        // ruleMatrix JSON 처리
        Map<String, Object> ruleMatrixData = (Map<String, Object>) requestData.get("ruleMatrix");
        System.out.println(ruleMatrixData);
        RuleMatrixForm ruleMatrixForm = new RuleMatrixForm();
        ruleMatrixForm.setFunctionId((Integer) ruleMatrixData.get("functionId"));
        ruleMatrixForm.setCellId((Integer) ruleMatrixData.get("cellId"));
        ruleMatrixForm.setDimen1Value((String) ruleMatrixData.get("dimen1Value"));
        ruleMatrixForm.setDimen2Value((String) ruleMatrixData.get("dimen2Value"));
        ruleMatrixForm.setDimen3Value((String) ruleMatrixData.get("dimen3Value"));
        ruleMatrixForm.setDimen4Value((String) ruleMatrixData.get("dimen4Value"));

        // 서비스 호출
        adminService.saveRuleSet(ruleSetForm);
        adminService.saveRuleMatrix(ruleMatrixForm);
        return ResponseEntity.ok().build();
    }


}
