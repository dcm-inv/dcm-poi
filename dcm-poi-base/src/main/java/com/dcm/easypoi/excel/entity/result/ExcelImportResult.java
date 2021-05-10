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
package com.dcm.easypoi.excel.entity.result;

import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

/**
 * 导入返回类
 *
 * @Author hourz
 * @since 2020-01-06
 */
@Data
public class ExcelImportResult<T> {

    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 失败数据
     */
    private List<T> failList;

    /**
     * 是否存在校验失败
     */
    private boolean verifyFail;

    /**
     * 数据源
     */
    private Workbook workbook;
    /**
     * 失败的数据源
     */
    private Workbook failWorkbook;

    private Map<String, Object> map;

    public ExcelImportResult() {

    }

    public ExcelImportResult(List<T> list, boolean verifyFail, Workbook workbook) {
        this.list = list;
        this.verifyFail = verifyFail;
        this.workbook = workbook;
    }

}
