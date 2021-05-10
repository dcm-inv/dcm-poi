package com.dcm.easypoi.pdf.styler;

import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的PDFstyler 实现
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class PdfExportStylerDefaultImpl implements IPdfExportStyler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfExportStylerDefaultImpl.class);

    @Override
    public void setCellStyler(Cell iCell, ExcelExportEntity entity, String text) {
        iCell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        iCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
    }

    @Override
    public PdfFont getFont(ExcelExportEntity entity, String text) {
        try {
            //用以支持中文
            return PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public PdfFont getFont() {
        return getFont(null, null);
    }

}
