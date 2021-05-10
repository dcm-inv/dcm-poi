package com.dcm.easypoi.handler.inter;

/**
 * Excel 本身数据文件
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IExcelDataModel {

    /**
     * 获取行号
     *
     * @return
     */
    public Integer getRowNum();

    /**
     * 设置行号
     *
     * @param rowNum
     */
    public void setRowNum(Integer rowNum);

}
