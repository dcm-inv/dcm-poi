package com.dcm.easypoi.excel.imports.sax.parse;

import com.dcm.easypoi.excel.entity.sax.SaxReadCellEntity;

import java.util.List;

/**
 * 解析数据
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface ISaxRowRead {
    /**
     * 解析数据
     * @param index
     * @param cellList
     */
    void parse(int index, List<SaxReadCellEntity> cellList);

}
