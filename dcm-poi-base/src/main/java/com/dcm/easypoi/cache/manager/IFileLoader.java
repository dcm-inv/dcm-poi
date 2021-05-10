package com.dcm.easypoi.cache.manager;

/**
 * Excel 缓存读取接口
 *
 * @Author hourz
 * @since 2018-06-06
 */
public interface IFileLoader {
    /**
     * 可以自定义KEY的作用
     *
     * @param key
     * @return
     */
    public byte[] getFile(String key);

}
