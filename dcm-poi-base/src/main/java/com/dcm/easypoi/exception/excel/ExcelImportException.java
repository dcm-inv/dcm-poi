package com.dcm.easypoi.exception.excel;

import com.dcm.easypoi.exception.excel.enums.ExcelImportEnum;

/**
 * 导入异常
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class ExcelImportException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ExcelImportEnum type;

    public ExcelImportException() {
        super();
    }

    public ExcelImportException(ExcelImportEnum type) {
        super(type.getMsg());
        this.type = type;
    }

    public ExcelImportException(ExcelImportEnum type, Throwable cause) {
        super(type.getMsg(), cause);
    }

    public ExcelImportException(String message) {
        super(message);
    }

    public ExcelImportException(String message, ExcelImportEnum type) {
        super(message);
        this.type = type;
    }

    public ExcelImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelImportEnum getType() {
        return type;
    }

    public void setType(ExcelImportEnum type) {
        this.type = type;
    }

}
