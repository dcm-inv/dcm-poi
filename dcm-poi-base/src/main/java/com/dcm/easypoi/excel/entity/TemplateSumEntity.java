package com.dcm.easypoi.excel.entity;

import lombok.Data;

/**
 * 统计对象
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Data
public class TemplateSumEntity {

    /**
     * CELL的值
     */
    private String cellValue;
    /**
     * 需要计算的KEY
     */
    private String sumKey;
    /**
     * 列
     */
    private int col;
    /**
     * 行
     */
    private int row;
    /**
     * 最后值
     */
    private double value;

}
