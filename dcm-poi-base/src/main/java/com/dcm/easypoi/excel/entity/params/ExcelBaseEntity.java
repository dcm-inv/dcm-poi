package com.dcm.easypoi.excel.entity.params;

import com.dcm.easypoi.entity.BaseTypeConstants;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Excel 导入导出基础对象类
 *
 * @Author hourz
 * @since 2020-01-06
 */
@Data
public class ExcelBaseEntity {
    /**
     * 对应name
     */
    protected String name;
    /**
     * 对应groupName
     */
    protected String groupName;
    /**
     * 对应type
     */
    private int type = BaseTypeConstants.STRING_TYPE;
    /**
     * 数据库格式
     */
    private String databaseFormat;
    /**
     * 导出日期格式
     */
    private String format;
    /**
     * 导出日期格式
     */
    private String[] replace;
    /**
     * 字典名称
     */
    private String dict;
    /**
     * set/get方法
     */
    private Method method;
    /**
     * 这个是不是超链接,如果是需要实现接口返回对象
     */
    private boolean hyperlink;
    /**
     * 固定的列
     */
    private Integer fixedIndex;
    /**
     * 时区
     */
    private String timezone;
    /**
     * 是否插入下拉
     */
    private boolean addressList;

    private List<Method> methods;

}
