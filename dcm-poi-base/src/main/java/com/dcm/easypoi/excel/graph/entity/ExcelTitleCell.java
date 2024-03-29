package com.dcm.easypoi.excel.graph.entity;

/**
 * Excel表头列
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class ExcelTitleCell {
    private Integer row;
    private Integer col;

    public ExcelTitleCell() {

    }

    public ExcelTitleCell(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }


}
