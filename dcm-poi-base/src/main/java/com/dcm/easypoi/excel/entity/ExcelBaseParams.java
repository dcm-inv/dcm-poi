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
package com.dcm.easypoi.excel.entity;

import com.dcm.easypoi.handler.inter.ICommentHandler;
import com.dcm.easypoi.handler.inter.IExcelDataHandler;
import com.dcm.easypoi.handler.inter.IExcelDictHandler;
import com.dcm.easypoi.handler.inter.II18nHandler;
import lombok.Data;

/**
 * 基础参数
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Data
public class ExcelBaseParams {

    /**
     * 数据处理接口,以此为主,replace,format都在这后面
     */
    private IExcelDataHandler dataHandler;

    /**
     * 字段处理类
     */
    private IExcelDictHandler dictHandler;
    /**
     * 国际化处理类
     */
    private II18nHandler i18nHandler;
    /**
     * 批注处理类
     */
    private ICommentHandler commentHandler;

}
