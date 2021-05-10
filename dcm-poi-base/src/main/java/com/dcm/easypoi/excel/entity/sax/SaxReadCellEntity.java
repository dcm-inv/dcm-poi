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
package com.dcm.easypoi.excel.entity.sax;

import com.dcm.easypoi.excel.entity.enmus.CellValueType;
import lombok.Data;

/**
 * Cell 对象
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Data
public class SaxReadCellEntity {
    /**
     * 值类型
     */
    private CellValueType cellType;
    /**
     * 值
     */
    private Object value;

    public SaxReadCellEntity(CellValueType cellType, Object value) {
        this.cellType = cellType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[type=" + cellType.toString( ) + ",value=" + value + "]";
    }

}
