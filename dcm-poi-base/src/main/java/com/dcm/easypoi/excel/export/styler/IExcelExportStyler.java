package com.dcm.easypoi.excel.export.styler;

import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import com.dcm.easypoi.excel.entity.params.ExcelForEachParams;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Excel导出样式接口
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IExcelExportStyler {

    /**
     * 列表头样式
     */
    public CellStyle getHeaderStyle(short headerColor);

    /**
     * 标题样式
     */
    public CellStyle getTitleStyle(short color);

    /**
     * 获取样式方法
     */
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity);

    /**
     * 获取样式方法
     *
     * @param dataRow 数据行
     * @param obj     对象
     * @param data    数据
     */
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data);

    /**
     * 模板使用的样式设置
     */
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams);

}
