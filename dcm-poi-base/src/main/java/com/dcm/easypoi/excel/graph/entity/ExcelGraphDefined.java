package com.dcm.easypoi.excel.graph.entity;

import com.dcm.easypoi.excel.graph.constant.ExcelGraphType;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel 图表默认值
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class ExcelGraphDefined implements ExcelGraph {
    private ExcelGraphElement category;
    public List<ExcelGraphElement> valueList = new ArrayList<>( );
    public List<ExcelTitleCell> titleCell = new ArrayList<>( );
    private Integer graphType = ExcelGraphType.LINE_CHART;
    public List<String> title = new ArrayList<>( );

    @Override
    public ExcelGraphElement getCategory() {
        return category;
    }

    public void setCategory(ExcelGraphElement category) {
        this.category = category;
    }

    @Override
    public List<ExcelGraphElement> getValueList() {
        return valueList;
    }

    public void setValueList(List<ExcelGraphElement> valueList) {
        this.valueList = valueList;
    }

    @Override
    public Integer getGraphType() {
        return graphType;
    }

    public void setGraphType(Integer graphType) {
        this.graphType = graphType;
    }

    @Override
    public List<ExcelTitleCell> getTitleCell() {
        return titleCell;
    }

    public void setTitleCell(List<ExcelTitleCell> titleCell) {
        this.titleCell = titleCell;
    }

    @Override
    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }


}
