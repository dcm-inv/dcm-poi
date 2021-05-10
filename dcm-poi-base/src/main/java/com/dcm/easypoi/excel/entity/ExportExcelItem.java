/**
 *
 */
package com.dcm.easypoi.excel.entity;

import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Excel 导出标题参数
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Data
public class ExportExcelItem {
    private List<ExcelExportEntity> entityList;
    private List<Map<String, Object>> resultList;
}
