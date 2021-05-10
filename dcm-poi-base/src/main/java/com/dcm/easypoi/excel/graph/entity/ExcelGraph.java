package com.dcm.easypoi.excel.graph.entity;

import java.util.List;

/**
 * Excel图表
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface ExcelGraph
{
	public ExcelGraphElement getCategory();
	public List<ExcelGraphElement> getValueList();
	public Integer getGraphType();
	public List<ExcelTitleCell> getTitleCell();
	public List<String> getTitle();
}
