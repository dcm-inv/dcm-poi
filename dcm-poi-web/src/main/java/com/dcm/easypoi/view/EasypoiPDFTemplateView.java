package com.dcm.easypoi.view;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcm.easypoi.util.WebFilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.dcm.easypoi.entity.vo.PDFTemplateConstants;
import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import com.dcm.easypoi.pdf.PdfExportUtil;
import com.dcm.easypoi.pdf.entity.PdfExportParams;

/**
 * PDF 导出 View
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Controller(PDFTemplateConstants.EASYPOI_PDF_TEMPLATE_VIEW)
public class EasypoiPDFTemplateView extends PoiBaseView {

    public EasypoiPDFTemplateView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final void renderMergedOutputModel(Map<String, Object> model,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        String fileName = "临时文件";
        ByteArrayOutputStream baos = createTemporaryOutputStream();
        PdfExportParams entity = (PdfExportParams) model.get(PDFTemplateConstants.PARAMS);
        Class<?> pojoClass = (Class<?>) model.get(PDFTemplateConstants.CLASS);
        Collection<?> dataSet = (Collection<?>) model.get(PDFTemplateConstants.DATA_LIST);
        List<ExcelExportEntity> entityList = (List<ExcelExportEntity>) model
                .get(PDFTemplateConstants.ENTITY_LIST);
        if (entityList == null) {
            PdfExportUtil.exportPdf(entity, pojoClass, dataSet, baos);
        } else {
            PdfExportUtil.exportPdf(entity, entityList, (Collection<? extends Map<?, ?>>) dataSet,
                    baos);
        }
        String userFileName = (String) model.get(PDFTemplateConstants.FILE_NAME);
        if (StringUtils.isNoneBlank(userFileName)) {
            fileName = userFileName;
        }
        // 用工具类生成符合RFC 5987标准的文件名header, 去掉UA判断
        response.setHeader("content-disposition", WebFilenameUtils.disposition(fileName + ".pdf"));
        writeToResponse(response, baos);
    }

}
