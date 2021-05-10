package com.dcm.easypoi.pdf;

import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import com.dcm.easypoi.pdf.entity.PdfExportParams;
import com.dcm.easypoi.pdf.export.PdfExportServer;
import com.dcm.easypoi.pdf.export.PdfTemplateServer;
import com.itextpdf.layout.Document;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * PDF 导出工具类
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class PdfExportUtil {

    /**
     * 根据注解导出数据
     *
     * @param entity    表格标题属性
     * @param pojoClass PDF对象Class
     * @param dataSet   PDF对象数据List
     */
    public static Document exportPdf(PdfExportParams entity, Class<?> pojoClass,
                                     Collection<?> dataSet, OutputStream outStream) {
        return new PdfExportServer(outStream, entity).createPdf(entity, pojoClass, dataSet);
    }

    /**
     * 根据Map创建对应的PDF
     *
     * @param entity     表格标题属性
     * @param entityList PDF对象Class
     * @param dataSet    PDF对象数据List
     */
    public static Document exportPdf(PdfExportParams entity, List<ExcelExportEntity> entityList,
                                     Collection<? extends Map<?, ?>> dataSet,
                                     OutputStream outStream) {

        return new PdfExportServer(outStream, entity).createPdfByExportEntity(entity, entityList,
                dataSet);
    }

    /**
     * 解析PDF版本
     *
     * @param url 模板地址
     * @param map 解析数据源
     * @return
     */
    public static Document exportPdf(String url, Map<String, Object> map, OutputStream outStream) throws Exception {
        return new PdfTemplateServer( ).parsePdf(url, map, outStream);
    }

}
