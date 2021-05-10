package com.dcm.easypoi.word.parse;

import com.dcm.easypoi.cache.WordCache;
import com.dcm.easypoi.entity.ImageEntity;
import com.dcm.easypoi.util.PoiElUtil;
import com.dcm.easypoi.util.PoiPublicUtil;
import com.dcm.easypoi.word.entity.MyXWPFDocument;
import com.dcm.easypoi.word.entity.params.ExcelListEntity;
import com.dcm.easypoi.word.parse.excel.ExcelEntityParse;
import com.dcm.easypoi.word.parse.excel.ExcelMapParse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 解析07版的Word,替换文字,生成表格,生成图片
 *
 * @Author hourz
 * @since 2018-01-06
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ParseWord07 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParseWord07.class);

    /**
     * 根据条件改变值
     *
     * @param map
     */
    private void changeValues(XWPFParagraph paragraph, XWPFRun currentRun, String currentText,
                              List<Integer> runIndex, Map<String, Object> map) throws Exception {
        // 判断是不是迭代输出
        if (currentText.contains(PoiElUtil.FOREACH) && currentText.startsWith(PoiElUtil.START_STR)) {
            currentText = currentText.replace(PoiElUtil.FOREACH, PoiElUtil.EMPTY).replace(PoiElUtil.START_STR, PoiElUtil.EMPTY).replace(PoiElUtil.END_STR, PoiElUtil.EMPTY);
            String[] keys = currentText.replaceAll("\\s{1,}", " ").trim( ).split(" ");
            List list = (List) PoiPublicUtil.getParamsValue(keys[0], map);
            list.forEach(obj -> {
                if (obj instanceof ImageEntity) {
                    currentRun.setText("", 0);
                    ExcelMapParse.addAnImage((ImageEntity) obj, currentRun);
                } else {
                    PoiPublicUtil.setWordText(currentRun, obj.toString( ));
                }
            });
        } else {
            Object obj = PoiPublicUtil.getRealValue(currentText, map);
            // 如果是图片就设置为图片
            if (obj instanceof ImageEntity) {
                currentRun.setText("", 0);
                ExcelMapParse.addAnImage((ImageEntity) obj, currentRun);
            } else {
                currentText = obj.toString( );
                PoiPublicUtil.setWordText(currentRun, currentText);
            }
        }

        for (int k = 0; k < runIndex.size( ); k++) {
            paragraph.getRuns( ).get(runIndex.get(k)).setText("", 0);
        }
        runIndex.clear( );
    }

    /**
     * 判断是不是迭代输出
     *
     * @return
     * @throws Exception
     */
    private Object checkThisTableIsNeedIterator(XWPFTableCell cell,
                                                Map<String, Object> map) throws Exception {
        String text = cell.getText( ).trim( );
        // 判断是不是迭代输出
        if (text != null && text.contains(PoiElUtil.FOREACH) && text.startsWith(PoiElUtil.START_STR)) {
            text = text.replace(PoiElUtil.FOREACH_NOT_CREATE, PoiElUtil.EMPTY).replace(PoiElUtil.FOREACH_AND_SHIFT, PoiElUtil.EMPTY)
                    .replace(PoiElUtil.FOREACH, PoiElUtil.EMPTY).replace(PoiElUtil.START_STR, PoiElUtil.EMPTY);
            String[] keys = text.replaceAll("\\s{1,}", " ").trim( ).split(" ");
            Object result = PoiPublicUtil.getParamsValue(keys[0], map);
            //添加list默认值，避免将{{$fe: list t.sn	t.hoby	t.remark}} 这类标签直接显示出来
            return Objects.nonNull(result) ? result : new ArrayList<Map<String, Object>>(0);
        }
        return null;
    }

    /**
     * 解析所有的文本
     *
     * @param paragraphs
     * @param map
     */
    private void parseAllParagraph(List<XWPFParagraph> paragraphs,
                                   Map<String, Object> map) throws Exception {
        XWPFParagraph paragraph;
        for (int i = 0; i < paragraphs.size( ); i++) {
            paragraph = paragraphs.get(i);
            if (paragraph.getText( ).indexOf(PoiElUtil.START_STR) != -1) {
                parseThisParagraph(paragraph, map);
            }

        }

    }

    /**
     * 解析这个段落
     *
     * @param paragraph
     * @param map
     */
    private void parseThisParagraph(XWPFParagraph paragraph, Map<String, Object> map) throws Exception {
        XWPFRun run;
        // 拿到的第一个run,用来set值,可以保存格式
        XWPFRun currentRun = null;
        // 存放当前的text
        String currentText = "";
        String text;
        // 判断是不是已经遇到{{
        Boolean isfinde = false;
        // 存储遇到的run,把他们置空
        List<Integer> runIndex = new ArrayList<Integer>( );
        for (int i = 0; i < paragraph.getRuns( ).size( ); i++) {
            run = paragraph.getRuns( ).get(i);
            text = run.getText(0);
            if (StringUtils.isEmpty(text)) {
                continue;
            } // 如果为空或者""这种这继续循环跳过
            if (isfinde) {
                currentText += text;
                if (currentText.indexOf(PoiElUtil.START_STR) == -1) {
                    isfinde = false;
                    runIndex.clear( );
                } else {
                    runIndex.add(i);
                }
                if (currentText.indexOf(PoiElUtil.END_STR) != -1) {
                    changeValues(paragraph, currentRun, currentText, runIndex, map);
                    currentText = "";
                    isfinde = false;
                }
                // 判断是不是开始
            } else if (text.indexOf(PoiElUtil.START_STR) >= 0) {
                currentText = text;
                isfinde = true;
                currentRun = run;
            } else {
                currentText = "";
            }
            if (currentText.indexOf(PoiElUtil.END_STR) != -1) {
                changeValues(paragraph, currentRun, currentText, runIndex, map);
                isfinde = false;
            }
        }

    }

    private void parseThisRow(List<XWPFTableCell> cells, Map<String, Object> map) throws Exception {
        for (XWPFTableCell cell : cells) {
            parseAllParagraph(cell.getParagraphs( ), map);
        }
    }


    /**
     * 解析这个表格
     *
     * @param table
     * @param map
     */
    private void parseThisTable(XWPFTable table, Map<String, Object> map) throws Exception {
        XWPFTableRow row;
        List<XWPFTableCell> cells;
        Object listobj;
        for (int i = 0; i < table.getNumberOfRows( ); i++) {
            row = table.getRow(i);
            cells = row.getTableCells( );
            listobj = checkThisTableIsNeedIterator(cells.get(0), map);
            if (listobj == null) {
                parseThisRow(cells, map);
            } else if (listobj instanceof ExcelListEntity) {
                new ExcelEntityParse( ).parseNextRowAndAddRow(table, i, (ExcelListEntity) listobj);
                //删除之后要往上挪一行,然后加上跳过新建的行数
                i = i + ((ExcelListEntity) listobj).getList( ).size( ) - 1;
            } else {
                ExcelMapParse.parseNextRowAndAddRow(table, i, (List) listobj);
                //删除之后要往上挪一行,然后加上跳过新建的行数
                i = i + ((List) listobj).size( ) - 1;
            }
        }
    }

    /**
     * 解析07版的Word并且进行赋值
     *
     * @return
     * @throws Exception
     */
    public XWPFDocument parseWord(String url, Map<String, Object> map) throws Exception {
        MyXWPFDocument doc = WordCache.getXWPFDocument(url);
        parseWordSetValue(doc, map);
        return doc;
    }

    /**
     * 解析07版的Work并且进行赋值但是进行多页拼接
     *
     * @param url
     * @param list
     * @return
     */
    public XWPFDocument parseWord(String url, List<Map<String, Object>> list) throws Exception {
        if (list == null || list.size( ) == 0) {
            return null;
        } else if (list.size( ) == 1) {
            return parseWord(url, list.get(0));
        } else {
            MyXWPFDocument doc = WordCache.getXWPFDocument(url);
            parseWordSetValue(doc, list.get(0));
            //插入分页
            doc.createParagraph( ).setPageBreak(true);
            for (int i = 1; i < list.size( ); i++) {
                MyXWPFDocument tempDoc = WordCache.getXWPFDocument(url);
                parseWordSetValue(tempDoc, list.get(i));
                tempDoc.createParagraph( ).setPageBreak(true);
                doc.getDocument( ).addNewBody( ).set(tempDoc.getDocument( ).getBody( ));

            }
            return doc;
        }

    }

    /**
     * 解析07版的Word并且进行赋值
     *
     * @throws Exception
     */
    public void parseWord(XWPFDocument document, Map<String, Object> map) throws Exception {
        parseWordSetValue((MyXWPFDocument) document, map);
    }

    private void parseWordSetValue(MyXWPFDocument doc, Map<String, Object> map) throws Exception {
        // 第一步解析文档
        parseAllParagraph(doc.getParagraphs( ), map);
        // 第二步解析页眉,页脚
        parseHeaderAndFoot(doc, map);
        // 第三步解析所有表格
        XWPFTable table;
        Iterator<XWPFTable> itTable = doc.getTablesIterator( );
        while (itTable.hasNext( )) {
            table = itTable.next( );
            if (table.getText( ).indexOf(PoiElUtil.START_STR) != -1) {
                parseThisTable(table, map);
            }
        }

    }

    /**
     * 解析页眉和页脚
     *
     * @param doc
     * @param map
     * @throws Exception
     */
    private void parseHeaderAndFoot(MyXWPFDocument doc, Map<String, Object> map) throws Exception {
        List<XWPFHeader> headerList = doc.getHeaderList( );
        for (XWPFHeader xwpfHeader : headerList) {
            for (int i = 0; i < xwpfHeader.getListParagraph( ).size( ); i++) {
                parseThisParagraph(xwpfHeader.getListParagraph( ).get(i), map);
            }
        }
        List<XWPFFooter> footerList = doc.getFooterList( );
        for (XWPFFooter xwpfFooter : footerList) {
            for (int i = 0; i < xwpfFooter.getListParagraph( ).size( ); i++) {
                parseThisParagraph(xwpfFooter.getListParagraph( ).get(i), map);
            }
        }

    }

}
