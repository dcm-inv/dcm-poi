package com.dcm.easypoi.pdf.styler;


import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.element.Cell;

/**
 * PDF导出样式设置
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IPdfExportStyler {

    /**
     * 设置Cell的样式
     *
     * @param entity
     * @param text
     */
    public void setCellStyler(Cell iCell, ExcelExportEntity entity, String text);

    /**
     * 获取字体
     *
     * @param entity
     * @param text
     */
    public PdfFont getFont(ExcelExportEntity entity, String text);

    /**
     * 获取字体
     */
    public PdfFont getFont();
}
