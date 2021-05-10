package com.dcm.easypoi.word.parse.excel;

import com.dcm.easypoi.entity.ImageEntity;
import com.dcm.easypoi.excel.entity.params.ExcelForEachParams;
import com.dcm.easypoi.util.PoiPublicUtil;
import com.dcm.easypoi.util.PoiWordStyleUtil;
import com.dcm.easypoi.word.entity.MyXWPFDocument;
import com.dcm.easypoi.util.PoiElUtil;
import com.google.common.collect.Maps;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理和生成Map 类型的数据变成表格
 *
 * @Author hourz
 * @since 2018-01-06
 */
public final class ExcelMapParse {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelMapParse.class);

    /**
     * 添加图片
     *
     * @param obj
     * @param currentRun
     * @throws Exception
     */
    public static void addAnImage(ImageEntity obj, XWPFRun currentRun) {
        try {
            Object[] isAndType = PoiPublicUtil.getIsAndType(obj);
            String picId;
            picId = currentRun.getDocument( ).addPictureData((byte[]) isAndType[0],
                    (Integer) isAndType[1]);
            if (obj.getLocationType( ) == ImageEntity.EMBED) {
                ((MyXWPFDocument) currentRun.getDocument( )).createPicture(currentRun,
                        picId, currentRun.getDocument( )
                                .getNextPicNameNumber((Integer) isAndType[1]),
                        obj.getWidth( ), obj.getHeight( ));
            } else if (obj.getLocationType( ) == ImageEntity.ABOVE) {
                ((MyXWPFDocument) currentRun.getDocument( )).createPicture(currentRun,
                        picId, currentRun.getDocument( )
                                .getNextPicNameNumber((Integer) isAndType[1]),
                        obj.getWidth( ), obj.getHeight( ), false);
            } else if (obj.getLocationType( ) == ImageEntity.BEHIND) {
                ((MyXWPFDocument) currentRun.getDocument( )).createPicture(currentRun,
                        picId, currentRun.getDocument( )
                                .getNextPicNameNumber((Integer) isAndType[1]),
                        obj.getWidth( ), obj.getHeight( ), true);
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage( ), e);
        }

    }


    private static void addAnImage(ImageEntity obj, XWPFTableCell cell) {
        List<XWPFParagraph> paragraphs = cell.getParagraphs( );
        XWPFParagraph newPara = paragraphs.get(0);
        XWPFRun imageCellRun = newPara.createRun( );
        addAnImage(obj, imageCellRun);
    }

    /**
     * 解析参数行,获取参数列表
     *
     * @param currentRow
     * @return
     */
    private static String[] parseCurrentRowGetParams(XWPFTableRow currentRow) {
        List<XWPFTableCell> cells = currentRow.getTableCells( );
        String[] params = new String[cells.size( )];
        String text;
        for (int i = 0; i < cells.size( ); i++) {
            text = cells.get(i).getText( );
            params[i] = text == null ? ""
                    : text.trim( ).replace(PoiElUtil.START_STR, PoiElUtil.EMPTY).replace(PoiElUtil.END_STR, PoiElUtil.EMPTY);
        }
        return params;
    }

    /**
     * 解析参数行,获取参数列表
     *
     * @param currentRow
     * @return
     */
    private static List<ExcelForEachParams> parseCurrentRowGetParamsEntity(XWPFTableRow currentRow) {
        List<XWPFTableCell> cells = currentRow.getTableCells( );
        List<ExcelForEachParams> params = new ArrayList<>( );
        String text;
        for (int i = 0; i < cells.size( ); i++) {
            ExcelForEachParams param = new ExcelForEachParams( );
            text = cells.get(i).getText( );
            param.setName(text == null ? ""
                    : text.trim( ).replace(PoiElUtil.START_STR, PoiElUtil.EMPTY).replace(PoiElUtil.END_STR, PoiElUtil.EMPTY));
            if (cells.get(i).getCTTc( ).getTcPr( ).getGridSpan( ) != null) {
                if (cells.get(i).getCTTc( ).getTcPr( ).getGridSpan( ).getVal( ) != null) {
                    param.setColspan(cells.get(i).getCTTc( ).getTcPr( ).getGridSpan( ).getVal( ).intValue( ));
                }
            }
            param.setHeight((short) currentRow.getHeight( ));
            params.add(param);
        }
        return params;
    }

    /**
     * 解析下一行,并且生成更多的行
     *
     * @param table
     * @param index
     * @param list
     */
    public static void parseNextRowAndAddRow(XWPFTable table, int index,
                                             List<Object> list) throws Exception {
        XWPFTableRow currentRow = table.getRow(index);
        //String[]                 params     = parseCurrentRowGetParams(currentRow);
        List<ExcelForEachParams> paramsList = parseCurrentRowGetParamsEntity(currentRow);
        String listname = paramsList.get(0).getName( );
        boolean isCreate = !listname.contains(PoiElUtil.FOREACH_NOT_CREATE);
        listname = listname.replace(PoiElUtil.FOREACH_NOT_CREATE, PoiElUtil.EMPTY).replace(PoiElUtil.FOREACH_AND_SHIFT, PoiElUtil.EMPTY)
                .replace(PoiElUtil.FOREACH, PoiElUtil.EMPTY).replace(PoiElUtil.START_STR, PoiElUtil.EMPTY);
        String[] keys = listname.replaceAll("\\s{1,}", " ").trim( ).split(" ");
        paramsList.get(0).setName(keys[1]);
        //保存这一行的样式是-后面好统一设置
        List<XWPFTableCell> tempCellList = new ArrayList<>( );
        tempCellList.addAll(table.getRow(index).getTableCells( ));
        int cellIndex = 0;
        Map<String, Object> tempMap = Maps.newHashMap( );
        LOGGER.debug("start for each data list :{}", list.size( ));
        for (Object obj : list) {
            currentRow = isCreate ? table.insertNewTableRow(index++) : table.getRow(index++);
            tempMap.put("t", obj);
            for (cellIndex = 0; cellIndex < currentRow.getTableCells( ).size( ); cellIndex++) {
                Object val = PoiElUtil.eval(paramsList.get(cellIndex).getName( ), tempMap);
                clearParagraphText(currentRow.getTableCells( ).get(cellIndex).getParagraphs( ));
                if (val instanceof ImageEntity) {
                    addAnImage((ImageEntity) val, tempCellList.get(cellIndex));
                } else {
                    PoiWordStyleUtil.copyCellAndSetValue(tempCellList.get(cellIndex),
                            currentRow.getTableCells( ).get(cellIndex), val.toString( ));
                }
            }

            for (; cellIndex < paramsList.size( ); cellIndex++) {
                Object val = PoiElUtil.eval(paramsList.get(cellIndex).getName( ), tempMap);
                XWPFTableCell cell = currentRow.createCell( );
                if (paramsList.get(cellIndex).getColspan( ) > 1) {
                    cell.getCTTc( ).addNewTcPr( ).addNewGridSpan( ).setVal(new BigInteger(paramsList.get(cellIndex).getColspan( ) + ""));
                }
                if (val instanceof ImageEntity) {
                    addAnImage((ImageEntity) val, cell);
                } else {
                    PoiWordStyleUtil.copyCellAndSetValue(tempCellList.get(cellIndex),
                            cell, val.toString( ));
                }
            }
        }
        table.removeRow(index);

    }

    private static void clearParagraphText(List<XWPFParagraph> paragraphs) {
        paragraphs.forEach(pp -> {
            if (pp.getRuns( ) != null) {
                for (int i = pp.getRuns( ).size( ) - 1; i >= 0; i--) {
                    pp.removeRun(i);
                }
            }
        });
    }

}
