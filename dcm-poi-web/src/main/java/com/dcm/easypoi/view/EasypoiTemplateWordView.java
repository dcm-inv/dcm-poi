package com.dcm.easypoi.view;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcm.easypoi.util.WebFilenameUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;

import com.dcm.easypoi.entity.vo.TemplateWordConstants;
import com.dcm.easypoi.word.WordExportUtil;

/**
 * Word模板视图
 *
 * @Author hourz
 * @since 2018-01-06
 */
@SuppressWarnings("unchecked")
@Controller(TemplateWordConstants.EASYPOI_TEMPLATE_WORD_VIEW)
public class EasypoiTemplateWordView extends PoiBaseView {

    private static final String CONTENT_TYPE = "application/msword";

    public EasypoiTemplateWordView() {
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件.docx";
        if (model.containsKey(TemplateWordConstants.FILE_NAME)) {
            codedFileName = (String) model.get(TemplateWordConstants.FILE_NAME) + ".docx";
        }
        // 用工具类生成符合RFC 5987标准的文件名header, 去掉UA判断
        response.setHeader("content-disposition", WebFilenameUtils.disposition(codedFileName));
        XWPFDocument document = WordExportUtil.exportWord07(
                (String) model.get(TemplateWordConstants.URL),
                (Map<String, Object>) model.get(TemplateWordConstants.MAP_DATA));
        ServletOutputStream out = response.getOutputStream( );
        document.write(out);
        out.flush( );
    }
}
