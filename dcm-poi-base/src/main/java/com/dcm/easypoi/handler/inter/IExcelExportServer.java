package com.dcm.easypoi.handler.inter;

import java.util.List;

/**
 * 导出数据接口
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IExcelExportServer {
    /**
     * 查询数据接口
     *
     * @param queryParams 查询条件
     * @param page        当前页数从1开始
     * @return
     */
    public List<Object> selectListForExcelExport(Object queryParams, int page);

}
