package com.dcm.easypoi.view;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;

import com.dcm.easypoi.entity.vo.NormalExcelConstants;
import com.dcm.easypoi.excel.ExcelExportUtil;
import com.dcm.easypoi.excel.entity.ExportParams;
import com.dcm.easypoi.excel.export.ExcelExportService;

/**
 * 生成解析器,减少用户操作
 *
 * @Author hourz
 * @since 2018-01-06
 */
@SuppressWarnings("unchecked")
@Controller(NormalExcelConstants.EASYPOI_EXCEL_VIEW)
public class EasypoiSingleExcelView extends MiniAbstractExcelView {

    public EasypoiSingleExcelView() {
        super();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        Workbook workbook = null;
        if (model.containsKey(NormalExcelConstants.MAP_LIST)) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) model
                    .get(NormalExcelConstants.MAP_LIST);
            if (list.size() == 0) {
                throw new RuntimeException("MAP_LIST IS NULL");
            }
            workbook = ExcelExportUtil.exportExcel(
                    (ExportParams) list.get(0).get(NormalExcelConstants.PARAMS), (Class<?>) list.get(0)
                            .get(NormalExcelConstants.CLASS),
                    (Collection<?>) list.get(0).get(NormalExcelConstants.DATA_LIST));
            for (int i = 1; i < list.size(); i++) {
                new ExcelExportService().createSheet(workbook,
                        (ExportParams) list.get(i).get(NormalExcelConstants.PARAMS), (Class<?>) list
                                .get(i).get(NormalExcelConstants.CLASS),
                        (Collection<?>) list.get(i).get(NormalExcelConstants.DATA_LIST));
            }
        } else {
            workbook = ExcelExportUtil.exportExcel(
                    (ExportParams) model.get(NormalExcelConstants.PARAMS),
                    (Class<?>) model.get(NormalExcelConstants.CLASS),
                    (Collection<?>) model.get(NormalExcelConstants.DATA_LIST));
        }
        if (model.containsKey(NormalExcelConstants.FILE_NAME)) {
            codedFileName = (String) model.get(NormalExcelConstants.FILE_NAME);
        }
        out(workbook, codedFileName, request, response);
    }
}
