package com.dcm.easypoi.handler.inter;

import com.dcm.easypoi.excel.entity.result.ExcelVerifyHandlerResult;

/**
 * 导入校验接口
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IExcelVerifyHandler<T> {

    /**
     * 导入校验方法
     *
     * @param obj
     *            当前对象
     * @return
     */
    public ExcelVerifyHandlerResult verifyHandler(T obj);

}
