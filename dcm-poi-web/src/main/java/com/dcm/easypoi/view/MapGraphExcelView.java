/**
 * Copyright 2013-2015 xfworld (xfworld@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dcm.easypoi.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;

import com.dcm.easypoi.entity.vo.MapExcelGraphConstants;
import com.dcm.easypoi.excel.ExcelExportUtil;
import com.dcm.easypoi.excel.entity.ExportParams;
import com.dcm.easypoi.excel.entity.params.ExcelExportEntity;
import com.dcm.easypoi.excel.graph.builder.ExcelChartBuildService;
import com.dcm.easypoi.excel.graph.entity.ExcelGraph;

/**
 * Map 对象接口、
 *
 * @Author hourz
 * @since 2018-01-06
 */
@SuppressWarnings("unchecked")
@Controller(MapExcelGraphConstants.MAP_GRAPH_EXCEL_VIEW)
public class MapGraphExcelView extends MiniAbstractExcelView {

    public MapGraphExcelView() {
        super();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        ExportParams params = (ExportParams) model.get(MapExcelGraphConstants.PARAMS);
        List<ExcelExportEntity> entityList = (List<ExcelExportEntity>) model.get(MapExcelGraphConstants.ENTITY_LIST);
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) model.get(MapExcelGraphConstants.MAP_LIST);
        List<ExcelGraph> graphDefinedList = (List<ExcelGraph>) model.get(MapExcelGraphConstants.GRAPH_DEFINED);
        //构建数据
        Workbook workbook = ExcelExportUtil.exportExcel(params, entityList, mapList);
        ExcelChartBuildService.createExcelChart(workbook, graphDefinedList, params.isDynamicData(), params.isAppendGraph());

        if (model.containsKey(MapExcelGraphConstants.FILE_NAME)) {
            codedFileName = (String) model.get(MapExcelGraphConstants.FILE_NAME);
        }
        out(workbook, codedFileName, request, response);
    }

}
