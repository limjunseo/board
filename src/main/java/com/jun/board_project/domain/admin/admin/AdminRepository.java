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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    public void saveCodeMeta(CodeMetaForm codeMetaForm) {
        String sql = "insert into code_meta(code_meta_id, code_meta_name) values(?, ?)";
        jdbcTemplate.update(sql, codeMetaForm.getCodeMetaId(), codeMetaForm.getCodeMetaName());
    }

    public void saveFunction(FunctionForm functionForm) {
        String sql = "insert into fnparam(function_id, dimen1, dimen2, dimen3, dimen4) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, functionForm.getFunctionId(), functionForm.getDimen1(), functionForm.getDimen2(), functionForm.getDimen3(), functionForm.getDimen4());
    }

    public void savePtBase(PtBaseForm ptBaseForm) {
        String sql = "insert into pt_base(pt_base_id, pt_base_name, function_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, ptBaseForm.getPtBaseId(), ptBaseForm.getPtBaseName(), ptBaseForm.getFunctionId());
    }

    public void saveRuleSet(RuleSetForm ruleSetForm) {
        String sql = "insert into rule_set(pt_base_id, function_id, cell_id, start_dt, end_dt, target_value) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ruleSetForm.getPtBaseId(), ruleSetForm.getFunctionId(), ruleSetForm.getCellId(), ruleSetForm.getStartDt(), ruleSetForm.getEndDt(), ruleSetForm.getTargetValue());
    }

    public void saveRuleMatrix(RuleMatrixForm ruleMatrixForm) {
        String sql = "insert into rule_matrix(function_id, cell_id, dimen1_value, dimen2_value, dimen3_value, dimen4_value) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ruleMatrixForm.getFunctionId(), ruleMatrixForm.getCellId(), ruleMatrixForm.getDimen1Value(), ruleMatrixForm.getDimen2Value(), ruleMatrixForm.getDimen3Value(), ruleMatrixForm.getDimen4Value());
    }

    public List<FunctionInfo> findAllFunction() {
        String sql = "select * from fnparam";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            FunctionInfo functionInfo = new FunctionInfo();
            functionInfo.setFunctionId(rs.getInt("function_id"));
            functionInfo.setDimen1(rs.getString("dimen1"));
            functionInfo.setDimen2(rs.getString("dimen2"));
            functionInfo.setDimen3(rs.getString("dimen3"));
            functionInfo.setDimen4(rs.getString("dimen4"));
            return functionInfo;
        });
    }

    public List<RuleMatrixInfo> findAllRuleMatrixByFunctionId(int functionId) {
        String sql =
                """
        SELECT * 
        FROM RULE_MATRIX 
        WHERE function_id = ?
            """;

        return jdbcTemplate.query(sql, new Object[]{functionId}, (rs, rowNum) -> {
            RuleMatrixInfo ruleMatrixInfo = new RuleMatrixInfo();
            ruleMatrixInfo.setFunctionId(rs.getInt("function_id"));
            ruleMatrixInfo.setCellId(rs.getInt("cell_id"));
            ruleMatrixInfo.setDimen1Value(rs.getString("dimen1_value"));
            ruleMatrixInfo.setDimen2Value(rs.getString("dimen2_value"));
            ruleMatrixInfo.setDimen3Value(rs.getString("dimen3_value"));
            ruleMatrixInfo.setDimen4Value(rs.getString("dimen4_value"));
            return ruleMatrixInfo;
        });
    }

    public FunctionInfo findFunction(int ptBaseId) {
        String sql = """
    
    SELECT f.function_id function_id, cm1.code_meta_name dimen1_name
        ,cm2.code_meta_name dimen2_name, cm3.code_meta_name dimen3_name , cm4.code_meta_name dimen4_name
        ,f.dimen1, f.dimen2, f.dimen3, f.dimen4
    FROM FNPARAM f, CODE_META cm1, CODE_META cm2, CODE_META cm3, CODE_META cm4
    WHERE f.dimen1  = cm1.code_meta_id
    AND f.dimen2 = cm2.code_meta_id
    AND f.dimen3 = cm3.code_meta_id
    AND f.dimen4 = cm4.code_meta_id
    AND f.function_id = ?
    
    """;

            return jdbcTemplate.queryForObject(sql, new Object[]{ptBaseId}, (rs, rowNum) -> {
            FunctionInfo functionInfo = new FunctionInfo();
            functionInfo.setFunctionId(rs.getInt("function_id"));
            functionInfo.setDimen1(rs.getString("dimen1"));
            functionInfo.setDimen2(rs.getString("dimen2"));
            functionInfo.setDimen3(rs.getString("dimen3"));
            functionInfo.setDimen4(rs.getString("dimen4"));
            functionInfo.setDimen1Name(rs.getString("dimen1_name"));
            functionInfo.setDimen2Name(rs.getString("dimen2_name"));
            functionInfo.setDimen3Name(rs.getString("dimen3_name"));
            functionInfo.setDimen4Name(rs.getString("dimen4_name"));
            return functionInfo;
        });

    }

    public String findCodeMetaName(String codeMetaId) {
        System.out.println("codeMetaId = " + codeMetaId);
        String sql = "select code_meta_name from code_meta where code_meta_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codeMetaId}, String.class);
    }

    public PtBaseInfo findPtBase(int ptBaseId) {
        String sql = "select * from pt_base where pt_base_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ptBaseId}, (rs, rowNum) -> {
            PtBaseInfo ptBaseInfo = new PtBaseInfo();
            ptBaseInfo.setPtBaseId(rs.getInt("pt_base_id"));
            ptBaseInfo.setPtBaseName(rs.getString("pt_base_name"));
            ptBaseInfo.setFunctionId(rs.getInt("function_id"));
            ptBaseInfo.setPtBaseDescription(rs.getString("pt_base_description"));
            return ptBaseInfo;
        });
    }

    public List<CodeMetaInfo> findAllCodeMeta() {
        String sql = "select * from code_meta";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CodeMetaInfo codeMetaInfo = new CodeMetaInfo();
            codeMetaInfo.setCodeMetaId(rs.getString("code_meta_id"));
            codeMetaInfo.setCodeMetaName(rs.getString("code_meta_name"));
            return codeMetaInfo;
        });
    }

    public List<PtBaseInfo> findAllPtBase() {
        String sql = "select * from pt_base";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PtBaseInfo ptBaseInfo = new PtBaseInfo();
            ptBaseInfo.setPtBaseId(rs.getInt("pt_base_id"));
            ptBaseInfo.setPtBaseName(rs.getString("pt_base_name"));
            ptBaseInfo.setFunctionId(rs.getInt("function_id"));
            return ptBaseInfo;
        });
    }


    public List<RuleSetInfo> findRuleSetByPtBaseId(int ptBaseId) {
        String sql =
         """
         SELECT * 
         FROM rule_set a, rule_matrix b
         WHERE a.cell_id = b.cell_id
         AND b.function_id = a.function_id
         AND pt_base_id = ?""";

        return jdbcTemplate.query(sql, new Object[]{ptBaseId}, (rs, rowNum) -> {
            RuleSetInfo ruleSetInfo = new RuleSetInfo();
            ruleSetInfo.setPtBaseId(rs.getInt("pt_base_id"));
            ruleSetInfo.setFunctionId(rs.getInt("function_id"));
            ruleSetInfo.setCellId(rs.getInt("cell_id"));
            ruleSetInfo.setStartDt(rs.getString("start_dt"));
            ruleSetInfo.setEndDt(rs.getString("end_dt"));
            ruleSetInfo.setTargetValue(rs.getInt("target_value"));
            ruleSetInfo.setDimen1Value(rs.getString("dimen1_value"));
            ruleSetInfo.setDimen2Value(rs.getString("dimen2_value"));
            ruleSetInfo.setDimen3Value(rs.getString("dimen3_value"));
            ruleSetInfo.setDimen4Value(rs.getString("dimen4_value"));
            return ruleSetInfo;
        });
    }

}
