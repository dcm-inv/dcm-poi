/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
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
package com.dcm.easypoi.excel;

import com.dcm.easypoi.excel.entity.ImportParams;
import com.dcm.easypoi.excel.entity.result.ExcelImportResult;
import com.dcm.easypoi.excel.imports.ExcelImportService;
import com.dcm.easypoi.excel.imports.sax.SaxReadExcel;
import com.dcm.easypoi.exception.excel.ExcelImportException;
import com.dcm.easypoi.handler.inter.IReadHandler;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Excel 导入工具
 *
 * @Author hourz
 * @since 2018-01-06
 */
@SuppressWarnings({"unchecked"})
public class ExcelImportUtil {

    private ExcelImportUtil() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportUtil.class);

    /**
     * Excel 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param file
     * @param pojoClass
     * @param params
     * @return
     */
    public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new ExcelImportService( ).importExcelByIs(in, pojoClass, params, false).getList( );
        } catch (ExcelImportException e) {
            throw new ExcelImportException(e.getType( ), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage( ), e);
            throw new ExcelImportException(e.getMessage( ), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Excel 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param inputstream
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(InputStream inputstream, Class<?> pojoClass,
                                          ImportParams params) throws Exception {
        return new ExcelImportService( ).importExcelByIs(inputstream, pojoClass, params, false).getList( );
    }

    /**
     * Excel 导入 数据源IO流 字段类型 Integer,Long,Double,Date,String,Boolean
     * 支持校验,支持Key-Value
     *
     * @param inputstream
     * @param pojoClass
     * @param params
     * @return
     * @throws Exception
     */
    public static <T> ExcelImportResult<T> importExcelMore(InputStream inputstream,
                                                           Class<?> pojoClass,
                                                           ImportParams params) throws Exception {
        return new ExcelImportService( ).importExcelByIs(inputstream, pojoClass, params, true);
    }

    /**
     * Excel 导入 数据源本地文件 字段类型 Integer,Long,Double,Date,String,Boolean
     * 支持校验,支持Key-Value
     * @param file
     * @param pojoClass
     * @param params
     * @return
     */
    public static <T> ExcelImportResult<T> importExcelMore(File file, Class<?> pojoClass,
                                                           ImportParams params) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new ExcelImportService( ).importExcelByIs(in, pojoClass, params, true);
        } catch (ExcelImportException e) {
            throw new ExcelImportException(e.getType( ), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage( ), e);
            throw new ExcelImportException(e.getMessage( ), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Excel 通过SAX解析方法,适合大数据导入,不支持图片
     * 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param inputstream
     * @param pojoClass
     * @param params
     * @param handler
     */
    public static void importExcelBySax(InputStream inputstream, Class<?> pojoClass,
                                        ImportParams params, IReadHandler handler) {
        new SaxReadExcel( ).readExcel(inputstream, pojoClass, params, handler);
    }

}
