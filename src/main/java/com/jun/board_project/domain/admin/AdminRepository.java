package com.jun.board_project.domain.admin;

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

    public void findFuntionById() {
        String sql = "select * from fnparam where funtion_id = ?";

    }

    public String findCodeMetaName(String codeMetaId) {
        String sql = "select code_meta_name from code_meta where code_meta_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codeMetaId}, String.class);
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

    public String findCodeMetaName(int codeMetaId) {
        String sql = "select code_meta_name from code_meta where code_meta_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codeMetaId}, String.class);
    }

    public List<RuleSetInfo> findRuleSetByPtBaseId(int ptBaseId) {
        String sql = """
         SELECT * 
         FROM rule_set a, fnparam b
         WHERE a.function_id = b.function_id
""";
        return jdbcTemplate.query(sql, new Object[]{ptBaseId}, (rs, rowNum) -> {
            RuleSetInfo ruleSetInfo = new RuleSetInfo();
            ruleSetInfo.setPtBaseId(rs.getInt("pt_base_id"));
            ruleSetInfo.setFunctionId(rs.getInt("function_id"));
            ruleSetInfo.setCellId(rs.getInt("cell_id"));
            ruleSetInfo.setStartDt(rs.getString("start_dt"));
            ruleSetInfo.setEndDt(rs.getString("end_dt"));
            ruleSetInfo.setTargetValue(rs.getInt("target_value"));
            ruleSetInfo.setDimen1(rs.getString("dimen1"));
            ruleSetInfo.setDimen2(rs.getString("dimen2"));
            ruleSetInfo.setDimen3(rs.getString("dimen3"));
            ruleSetInfo.setDimen4(rs.getString("dimen4"));
            return ruleSetInfo;
        });
    }

}
