package com.dcm.easypoi.handler.inter;

/**
 * 国际化接口
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface II18nHandler {

    /**
     * 获取当前名称
     *
     * @param name 注解配置的
     * @return 返回国际化的名字
     */
    public String getLocaleName(String name);

}
