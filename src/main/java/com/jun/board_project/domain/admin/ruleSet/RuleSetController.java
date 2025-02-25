package com.jun.board_project.domain.admin.ruleSet;

import com.jun.board_project.domain.admin.admin.AdminService;
import com.jun.board_project.domain.admin.function.FunctionInfo;
import com.jun.board_project.domain.admin.pointBase.PtBaseInfo;
import com.jun.board_project.domain.admin.ruleMatrix.RuleMatrixForm;
import lombok.RequiredArgsConstructor;
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
        System.out.println(requestData);

        // ruleSet JSON 처리
        Map<String, Object> ruleSetData = (Map<String, Object>) requestData.get("ruleSet");
        System.out.println(ruleSetData);

        RuleSetForm ruleSetForm = new RuleSetForm();
        ruleSetForm.setPtBaseId(Integer.parseInt(ruleSetData.get("ptBaseId").toString()));
        ruleSetForm.setFunctionId(Integer.parseInt(ruleSetData.get("functionId").toString()));
        ruleSetForm.setCellId(Integer.parseInt(ruleSetData.get("cellId").toString()));
        ruleSetForm.setTargetValue(Integer.parseInt(ruleSetData.get("targetValue").toString()));

        ruleSetForm.setEndDt("99991231");
        ruleSetForm.setStartDt(LocalDate.now().format(formatter));

        // ruleMatrix JSON 처리
        Map<String, Object> ruleMatrixData = (Map<String, Object>) requestData.get("ruleMatrix");
        System.out.println(ruleMatrixData);

        RuleMatrixForm ruleMatrixForm = new RuleMatrixForm();
        ruleMatrixForm.setFunctionId(Integer.parseInt(ruleMatrixData.get("functionId").toString()));
        ruleMatrixForm.setCellId(Integer.parseInt(ruleMatrixData.get("cellId").toString()));
        ruleMatrixForm.setDimen1Value(ruleMatrixData.get("dimen1Value").toString());
        ruleMatrixForm.setDimen2Value(ruleMatrixData.get("dimen2Value").toString());
        ruleMatrixForm.setDimen3Value(ruleMatrixData.get("dimen3Value").toString());
        ruleMatrixForm.setDimen4Value(ruleMatrixData.get("dimen4Value").toString());

        // 서비스 호출
        adminService.saveRuleSet(ruleSetForm);
        adminService.saveRuleMatrix(ruleMatrixForm);
        return ResponseEntity.ok().build();
    }



}
