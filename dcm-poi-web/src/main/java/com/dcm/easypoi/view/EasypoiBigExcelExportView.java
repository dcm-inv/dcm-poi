package com.dcm.easypoi.view;

import com.dcm.easypoi.entity.vo.BigExcelConstants;
import com.dcm.easypoi.excel.ExcelExportUtil;
import com.dcm.easypoi.excel.entity.ExportParams;
import com.dcm.easypoi.handler.inter.IExcelExportServer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Excel 生成解析器,减少用户操作
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Controller(BigExcelConstants.EASYPOI_BIG_EXCEL_VIEW)
public class EasypoiBigExcelExportView extends MiniAbstractExcelView {

    public EasypoiBigExcelExportView() {
        super();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        Workbook workbook = ExcelExportUtil.exportBigExcel(
                (ExportParams) model.get(BigExcelConstants.PARAMS),
                (Class<?>) model.get(BigExcelConstants.CLASS),
                (IExcelExportServer) model.get(BigExcelConstants.DATA_INTER),
                model.get(BigExcelConstants.DATA_PARAMS));
        if (model.containsKey(BigExcelConstants.FILE_NAME)) {
            codedFileName = (String) model.get(BigExcelConstants.FILE_NAME);
        }
        out(workbook, codedFileName, request, response);
    }
}
