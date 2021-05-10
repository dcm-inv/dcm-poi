package com.dcm.easypoi.view;

import com.dcm.easypoi.entity.vo.NormalExcelConstants;
import com.dcm.easypoi.entity.vo.TemplateExcelConstants;
import com.dcm.easypoi.excel.ExcelExportUtil;
import com.dcm.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Excel模板视图
 *
 * @Author hourz
 * @since 2018-01-06
 */
@SuppressWarnings("unchecked")
@Controller(TemplateExcelConstants.EASYPOI_TEMPLATE_EXCEL_VIEW)
public class EasypoiTemplateExcelView extends MiniAbstractExcelView {

    public EasypoiTemplateExcelView() {
        super();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        @SuppressWarnings("deprecation")
        Workbook workbook = ExcelExportUtil.exportExcel(
                (TemplateExportParams) model.get(TemplateExcelConstants.PARAMS),
                (Class<?>) model.get(TemplateExcelConstants.CLASS),
                (List<?>) model.get(TemplateExcelConstants.LIST_DATA),
                (Map<String, Object>) model.get(TemplateExcelConstants.MAP_DATA));
        if (model.containsKey(NormalExcelConstants.FILE_NAME)) {
            codedFileName = (String) model.get(NormalExcelConstants.FILE_NAME);
        }
        out(workbook, codedFileName, request, response);
    }
}
