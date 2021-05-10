package com.dcm.easypoi.cache;

import com.dcm.easypoi.excel.entity.ExcelToHtmlParams;
import com.dcm.easypoi.excel.html.ExcelToHtmlService;

/**
 * Excel 转变成为Html 的缓存
 *
 * @Author hourz
 * @since 2018-08-06
 */
public class HtmlCache {

    public static String getHtml(ExcelToHtmlParams params) {
        try {
            return new ExcelToHtmlService(params).printPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
