package com.dcm.easypoi.exception.word;

import com.dcm.easypoi.exception.word.enmus.WordExportEnum;

/**
 * word导出异常
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class WordExportException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WordExportException() {
        super();
    }

    public WordExportException(String msg) {
        super(msg);
    }

    public WordExportException(WordExportEnum exception) {
        super(exception.getMsg( ));
    }

}
